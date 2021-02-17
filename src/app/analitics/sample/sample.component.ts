import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router,ActivatedRoute } from '@angular/router';
import { AnalystService } from 'src/app/core/services/analyst.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-sample',
  templateUrl: './sample.component.html',
  styleUrls: ['./sample.component.scss']
})

export class SampleComponent implements OnInit{
  
  sub: any;
  id: number;
  element:any;
  dataSource:any;
  telefono:any;
  displayedColumns: string[] = ['nombreUsuario','apellidoUsuario','numeroTelefono','button','icons'];
  constructor(private route: ActivatedRoute,private router: Router, private analistService:AnalystService,public _snackBar: MatSnackBar) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getMuestras();
  }
  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getMuestras(){
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      let userStorage = localStorage.getItem('analistaLogged');
      let user = JSON.parse(userStorage);
      let token = user.token;
      this.analistService.getSample(this.id,token).
      subscribe(
        res =>{
          let auxRes:any = res;
          if(auxRes.estado == 'success'){
            this.element = auxRes.encuestados;
            this.dataSource = new MatTableDataSource(auxRes.encuestados);
            this.dataSource.paginator = this.paginator;
          }
        },
        err =>{
          console.log(err)
        }
      )
    });

  }

  addMuestra(){
    this.router.navigate(['/analitics/addsample/'+this.id]);
  }

  deleteMuestra(idUsuario){
    console.log(idUsuario)
  }

  updateMuestra(idUsuario){
    this.router.navigate(['/analitics/updatesample/'+idUsuario]);
    console.log(idUsuario)
  }

  interviewMuestra(encuestadoId,solicitudId){
    this.router.navigate(['/analitics/interview/'+encuestadoId+'/'+solicitudId]);
  }

}