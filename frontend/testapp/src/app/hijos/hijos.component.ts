import { Component, OnInit, Input} from '@angular/core';
import { Hijo } from 'hijos';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-hijos',
  templateUrl: './hijos.component.html',
  styleUrls: ['./hijos.component.scss']
})
export class HijosComponent implements OnInit {
  hijos: Hijo[];

  @Input() fechaNacimiento: Date;
  @Input() sexo: String;
  @Input() estadoCivil: String;
  edad: number;

  constructor(private modalService: NgbModal) {
    this.hijos = [];
  }
  
  ngOnInit() {}
  
  openVerticallyCentered(content) {
    this.modalService.open(content, { centered: true });
  }

  calcularEdad(){
    const today = new Date();
    const fechaNac = new Date(this.fechaNacimiento);
    let age = today.getFullYear() - fechaNac.getFullYear();
    const m = today.getMonth() - fechaNac.getMonth();
    if(m < 0 || (m === 0 && today.getDate() < fechaNac.getDate())){
      age--;
    }
    this.edad = age;
  }

  addHijo(){
    const hijo = new Hijo();
    hijo.edad = this.edad;
    hijo.estadoCivil = this.estadoCivil;
    hijo.sexo = this.sexo;
    this.hijos.push(hijo);
    console.log(hijo);
  }
  deleteHijo(index: number){
    this.hijos.splice(index, 1);
  }
}
