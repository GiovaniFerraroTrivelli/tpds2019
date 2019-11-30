import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Poliza } from './poliza/poliza';
import { Cliente } from './cliente/cliente';

@Injectable({
  providedIn: 'root'
})
export class DatashareService {
  private sendPoliza = new BehaviorSubject(null);
  poliza = this.sendPoliza.asObservable();

  private sendCliente = new BehaviorSubject(null);
  cliente = this.sendCliente.asObservable();

  constructor() { }
  
  changePolizaMessage(poliza: Poliza){
    this.sendPoliza.next(poliza);
  }
  changeClienteMessage(cliente: Cliente){
    this.sendCliente.next(cliente);
  }
}
