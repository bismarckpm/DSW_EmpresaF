import { Component,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-study-request',
  templateUrl: './study-request.component.html',
  styleUrls: ['./study-request.component.scss']
})
export class StudyRquestComponent implements OnInit {
  subCategorias: any;
  createEstudioForm: FormGroup;
  constructor(private formBuilder: FormBuilder) { }


  ngOnInit(): void {
    this.createEstudioForm = this.formBuilder.group({
      selectSubCategoria: ['',Validators.required],
      nivelsocioeconomico: ['', Validators.required],
      genero: ['', Validators.required],
      rangosuperior: ['', Validators.required],
      rangoinferior: ['', Validators.required]
    });
    this.getInfo();
  }

  getInfo(){

    this.subCategorias = 
    [
      { idSubcategoria: 1, name: 'Cuidado personal'},
      { idSubcategoria: 2, name: 'Shampo' },
      { idSubcategoria: 3, name: 'Detergene'}
    ]
  }

  handleCreateEstudio(){

  }
}
