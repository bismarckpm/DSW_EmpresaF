import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { AnalystService } from 'src/app/core/services/analyst.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-menu-analitics',
  templateUrl: './menu-analitics.component.html',
  styleUrls: ['./menu-analitics.component.scss']
})

export class MenuAnaliticsComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['id','edadInicial','edadFinal','genero','estado','marca','tipoMarca','unidadMarca','icons'];
  constructor(private analisService:AnalystService,public _snackBar: MatSnackBar) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getEstudios();
    
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  getEstudios(){
    let analistStorage = localStorage.getItem('analistaLogged');
    let analista = JSON.parse(analistStorage);
    analista = analista.id; 
    this.analisService.getSolicitudes(analista)
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
            this.element = auxRes.solicitudes;
            this.dataSource = new MatTableDataSource(auxRes.solicitudes);
            this.dataSource.paginator = this.paginator;
        }
      },
      err => {
        console.log(err)
      }
    )

  }

  activarSolicitud(idSolicitud:number){
    this.analisService.activarSolicitud(idSolicitud)
    .subscribe(
      res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Solicitud activada");
          }
          else{
            this.openSnackBar("Ocurrio un problema");
          }
      },
      err => {
        console.log(err)
      }
    )
  }

  generateResults(idEstudio){
    console.log(idEstudio)
  }

  getMuestra(idEstudio){
    console.log(idEstudio)
  }

}