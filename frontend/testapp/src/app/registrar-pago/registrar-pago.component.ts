import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente/cliente';
import { Poliza } from '../poliza/poliza';
import { Cuota } from '../poliza/cuota';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Documento } from '../cliente/documento';
import { DatashareService } from '../datashare.service'

@Component({
  selector: 'app-registrar-pago',
  templateUrl: './registrar-pago.component.html',
  styleUrls: ['./registrar-pago.component.scss']
})
export class RegistrarPagoComponent implements OnInit {
  private cliente: Cliente;
  private poliza: Poliza;
  private index: number;
  //private cuotas: Cuota[];
  cuotas = [{'asd':'asd'},{'asd':'asd'},{'asd':'asd'}]
  
  constructor(private modal: NgbModal, private data: DatashareService) { 
    this.index = 0;
    this.poliza = new Poliza();
    this.cliente = new Cliente();
    this.cliente.documento = new Documento();
  }

  ngOnInit() {
    /*this.data.poliza.subscribe( poliza => this.poliza = poliza);
    this.data.cliente.subscribe( cliente => this.cliente = cliente);
    console.log(this.poliza);
    console.log(this.cliente);*/
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
