import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente/cliente';
import { Poliza } from '../poliza/poliza';
import { Cuota } from '../poliza/cuota';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Documento } from '../cliente/documento';
import { DatashareService } from '../datashare.service'
import { ResumenPoliza } from '../poliza/resumen-poliza';

@Component({
  selector: 'app-registrar-pago',
  templateUrl: './registrar-pago.component.html',
  styleUrls: ['./registrar-pago.component.scss']
})
export class RegistrarPagoComponent implements OnInit {
  private index: number;
  private resumenPoliza: ResumenPoliza;
  private cuotas: Cuota[];
  private nroPoliza: string;
  private nroCliente: string;
  
  constructor(private modal: NgbModal, private data: DatashareService) { 
    this.index = 0;
  }

  ngOnInit() {
    this.data.poliza.subscribe(poliza => this.resumenPoliza = poliza);
    this.data.nroPoliza.subscribe(nroPoliza => this.nroPoliza = nroPoliza);
    this.data.nroCliente.subscribe(nroCliente => this.nroCliente = nroCliente);
    this.cuotas = this.resumenPoliza.cuotas;
    console.log(this.resumenPoliza);
    console.log(this.nroPoliza);
    console.log(this.nroCliente);
  }

  disableCheckbox(index){
    if(index > this.index || index < this.index-1){
      return true;
    }
    else return false;
  }

  onChecked(isChecked: boolean){
    isChecked? this.index++ : this.index--;
  }

  openModal(content) {
    this.modal.open(content, {centered: true});
  }

}
