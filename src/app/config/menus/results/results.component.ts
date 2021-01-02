import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/core/services/admin.service';
import { Router,ActivatedRoute } from '@angular/router';
import * as Highcharts from 'highcharts';
import highcharts3D from 'highcharts/highcharts-3d';
highcharts3D(Highcharts);

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.scss']
})
export class ResultsComponent implements OnInit {

  sub: any;
  id: number;
  //charts
  highcharts: typeof Highcharts = Highcharts;
  chartOptions: Highcharts.Options[] = [];
  chart(enunciado: any, valor: any): Highcharts.Options {

    let chartOptions: Highcharts.Options = {
  
      chart: {
        type: "pie",
        plotShadow: false,
        options3d: {
          enabled: true,
          alpha: 45,
          beta: 0,
      },
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
  constructor(private adminService:AdminService,private router: Router,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getRespuestaEncuesta();
  }

  getRespuestaEncuesta(){
    this.sub = this.route.params.subscribe(params => {
    this.id = +params['id'];
    })
    this.adminService.getRespuestaEncuesta(this.id)
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        this.respuesta = auxRes.respuestas
        if(auxRes.estado == 'success'){
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
