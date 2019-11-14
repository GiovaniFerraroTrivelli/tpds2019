import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente/cliente';
import { Poliza } from '../poliza/poliza';
import { Cuota } from '../poliza/cuota';

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
  
  constructor() { 
    this.index = 0;
  }

  ngOnInit() {
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
}
