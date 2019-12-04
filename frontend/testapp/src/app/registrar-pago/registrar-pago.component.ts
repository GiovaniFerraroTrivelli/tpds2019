import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente/cliente';
import { Poliza } from '../poliza/poliza';
import { Cuota } from '../poliza/cuota';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Documento } from '../cliente/documento';
import { DatashareService } from '../datashare.service'
import { ResumenPoliza } from '../poliza/resumen-poliza';
import { Subscription } from 'rxjs';

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
  private vigencia: string;
  private vencimientoCuota: string;

  months = {
    1: "Enero",
    2: "Febrero",
    3: "Marzo",
    4: "Abril",
    5: "Mayo",
    6: "Junio",
    7: "Julio",
    8: "Agosto",
    9: "Septiembre",
    10: "Octubre",
    11: "Noviembre",
    12: "Diciembre"
  };

  constructor(private modal: NgbModal, private data: DatashareService) { 
    this.index = 0;
    this.data.poliza.subscribe(poliza => this.resumenPoliza = poliza);
    this.data.nroPoliza.subscribe(nroPoliza => this.nroPoliza = nroPoliza);
    this.data.nroCliente.subscribe(nroCliente => this.nroCliente = nroCliente);
    this.cuotas = this.resumenPoliza.cuotas;
  }

  ngOnInit() {
  }

  setVigencia(){
    return this.months[parseInt(this.resumenPoliza.finVigencia[5]+this.resumenPoliza.finVigencia[6])] + ' '+
           this.resumenPoliza.finVigencia.substr(0,4); 
  }

  getVencimientoCuota(i){
    return this.cuotas[i].fechaVencimiento.slice(8,10) + '/' +  this.cuotas[i].fechaVencimiento.slice(5,7) + '/' + this.cuotas[i].fechaVencimiento.slice(0,4)
  }

  mesCuota(i){
    return this.months[parseInt(this.cuotas[i].fechaVencimiento[5]+this.cuotas[i].fechaVencimiento[6])];
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
  hayCuotas(){
    if(this.cuotas == null){
      return false;
    } 
    else return true;
  }
}
