import { Component, OnInit, Output, Input } from '@angular/core';
import { TipoCobertura } from 'TipoCobertura';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TipocoberturaService } from './tipocobertura.service';

@Component({
  selector: 'app-tipocobertura',
  templateUrl: './tipocobertura.component.html',
  styleUrls: ['./tipocobertura.component.scss']
})
export class TipocoberturaComponent implements OnInit {
  @Input() coberturas: TipoCobertura[];
  coberturaSeleccionada: String;

  constructor(private modalService: NgbModal, private coberturaService: TipocoberturaService) { }

  openVerticallyCentered(content) {
    this.modalService.open(content, { centered: true });
  }
  seleccionarCobertura(cobertura){
    this.coberturaSeleccionada = cobertura;
  }
  onSubmit(){
    this.coberturas.forEach(element => {
      if (element.nombre == this.coberturaSeleccionada){
        console.log(element);
      }
    });
  }
  
  ngOnInit() {
  }
}
