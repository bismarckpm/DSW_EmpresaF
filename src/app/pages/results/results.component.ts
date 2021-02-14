import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { UsersService } from 'src/app/core/services/users.service';
import * as Highcharts from 'highcharts';
import highcharts3D from 'highcharts/highcharts-3d';

export interface DataItem  {
  pregunta: any;
  respuesta:any
  
};

type ObjType = {
  data: DataItem[]
};

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.scss']
})
export class ResultsComponent implements OnInit {
  sub: any;
  id: number;
  resDesarrollo:any;
  //charts
  highcharts: typeof Highcharts = Highcharts;
  chartOptions: Highcharts.Options[] = [];
  chart(enunciado: any, valor: any): Highcharts.Options {

    let chartOptions: Highcharts.Options = {
  
      chart: {
        type: "pie",
        plotShadow: false,
        
    },
    title: {
        text: enunciado
    },
    tooltip: {
      headerFormat: "",
      pointFormat:
        "<span style='color:{point.color}'>\u25CF</span> {point.name}: <b>{point.y}</b>",
      style: {
        fontSize: '10px'
      },
    },
    plotOptions : {
      pie: {
        shadow: true,
        center: ["50%", "50%"],
        depth: 25,
        innerSize: "20%",
        allowPointSelect: true,
        cursor: 'pointer',
         dataLabels: {
            enabled: false
         },
         showInLegend: true
      }
   },
    series: [
      {
        type: "pie",
        data: valor 
      }
    ]
  
  };
    return chartOptions;
  }

   nombre: any[] = [];
  respuesta:any;
  constructor(private usersService:UsersService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getRespuestaEncuesta();
  }

  private readonly obj: ObjType = {
    data: []
  };

  getRespuestaEncuesta(){
    this.sub = this.route.params.subscribe(params => {
    this.id = +params['id'];
    })
    var i:number = 0;
    let userStorage = localStorage.getItem('clientLogged');
    let user = JSON.parse(userStorage);
    let token = user.token; 
    this.usersService.getRespuestaEncuesta(this.id,token)
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        this.respuesta = auxRes.respuestas
        if(auxRes.estado == 'success'){
          for(let item in this.respuesta){
            if(this.respuesta[item].tipoPregunta == 'desarrollo'){
              const dataCopy : DataItem = {
                pregunta: this.respuesta[item].pregunta,
                respuesta: this.respuesta[item].respuesta
              };
              this.obj.data[i] = dataCopy;
              i++;
            }
          }
          this.resDesarrollo = this.obj.data;
            this.respuesta.forEach(element => {
              if(element.tipoPregunta != 'desarrollo'){
                const valor = element.opciones.map((x:any) => { return {name: x.opcion, y: x.conteo} })
                const enunciado = element.pregunta;

                this.chartOptions.push( this.chart(enunciado, valor ) );
              }

            })
          
        }
      },
      err => {
        console.log(err)
      }
    )
  }

}
