import { Component,OnInit, ViewChild} from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/core/services/admin.service';

@Component({
  selector: 'app-menu-requestStudy',
  templateUrl: './menu-requestStudy.component.html',
  styleUrls: ['./menu-requestStudy.component.scss']
})

export class RequestStudyComponent implements OnInit{
    studies:any;
    constructor(private router: Router,private adminService:AdminService) { }
  
    ngOnInit(): void {
      this.getEstudios();
      
    }
  
    getEstudios(){
      let adminStorage = localStorage.getItem('administrador');
      let admin = JSON.parse(adminStorage);
      admin = admin.id;
      this.adminService.getRequestedStudies(admin)
      .subscribe(
          res => {
            let auxRes:any;
            auxRes = res;
            if(auxRes.estado == 'success'){
                this.studies = auxRes.solicitudes;
            }
          },
          err => {
            console.log(err)
          }
      )
  
    }
  
    asignarEstudio(idEstudio){
      this.router.navigate(['/config/newStudy/'+idEstudio]);
    }
  
    solicitarEstudio(){
      this.router.navigate(['/pages/request-study']);
    }
 

}