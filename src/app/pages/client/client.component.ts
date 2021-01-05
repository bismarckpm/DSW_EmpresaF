import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';
import { UsersService } from 'src/app/core/services/users.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.scss']
})


export class ClientComponent implements OnInit{
  elements:any;
  dataSource:any;
  panelOpenState = false;
  displayedColumns: string[] = ['idEstudio', 'estatusEstudio', 'edad', 'fechaIniEstudio','genero','icons'];
  constructor(private router: Router, private userService:UsersService) { }

  ngOnInit(): void {
    this.getEstudios();
    
  }

  getEstudios(){
    let userStorage = localStorage.getItem('clientLogged');
    let user = JSON.parse(userStorage);
    user = user.id;  
    this.userService.getSpecificStudies(user)
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        console.log(auxRes)
        if(auxRes.estado == 'success'){
          this.elements = auxRes.solicitudes
        }
      },
      err => {
        console.log(err)
      }
    )
  }

  updateEstudio(idEstudio){
    console.log(idEstudio)
  }

  solicitarEstudio(){
    this.router.navigate(['/pages/request-study']);
  }
}
