import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ResumenPoliza } from './poliza/resumen-poliza'

@Injectable({
  providedIn: 'root'
})
export class DatashareService {
  private sendPoliza = new BehaviorSubject(null);
  poliza = this.sendPoliza.asObservable();

  private sendNroPoliza = new BehaviorSubject(null);
  nroPoliza = this.sendNroPoliza.asObservable();

  private sendNroCliente = new BehaviorSubject(null);
  nroCliente = this.sendNroCliente.asObservable();

  constructor() { }
  
  changePolizaMessage(poliza: ResumenPoliza){
    this.sendPoliza.next(poliza);
  }
  changeNroPoliza(nroPoliza: string){
    this.sendNroPoliza.next(nroPoliza);
  }
  changeNroCliente(nroCliente: string){
    this.sendNroCliente.next(nroCliente);
  }
}
