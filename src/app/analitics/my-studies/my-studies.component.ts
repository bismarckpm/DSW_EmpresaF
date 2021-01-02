import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';
import { AnalystService } from 'src/app/core/services/analyst.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-my-studies',
  templateUrl: './my-studies.component.html',
  styleUrls: ['./my-studies.component.scss']
})
export class MyStudiesComponent implements OnInit {
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['id', 'nombreEstudio','icons'];

  constructor(private router: Router, private analistService:AnalystService,public _snackBar: MatSnackBar) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getMyStudies();
    
  }
  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  getMyStudies(){
    let analistStorage = localStorage.getItem('analistaLogged');
    let analista = JSON.parse(analistStorage);
    analista = analista.id; 
    this.analistService.getMyStudies(analista)
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.element = auxRes.estudios;
          this.dataSource = new MatTableDataSource(auxRes.estudios);
          this.dataSource.paginator = this.paginator;
        }
      },
      err => {
        console.log(err)
      }
    )
  }

  verResultado(encuestaId){
    this.router.navigate(['/analitics/results/'+encuestaId]);
  }

}
