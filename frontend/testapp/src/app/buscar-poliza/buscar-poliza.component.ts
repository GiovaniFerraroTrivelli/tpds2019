import { Component, OnInit } from '@angular/core';
import { BuscarpolizaService } from './buscarpoliza.service'
import { Poliza } from '../poliza/poliza';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from "@angular/router";
import { Cliente } from '../cliente/cliente';
import { DatashareService } from '../datashare.service'

@Component({
  selector: 'app-buscar-poliza',
  templateUrl: './buscar-poliza.component.html',
  styleUrls: ['./buscar-poliza.component.scss']
})
export class BuscarPolizaComponent implements OnInit {

  constructor(
    private BuscarpolizaService: BuscarpolizaService,
    private modalService: NgbModal,
    private router: Router,
    private data: DatashareService
    ) {}
  private idPoliza: number;
  private polizaSeleccionada: Poliza;
  /*private listaPolizas: Poliza[];*/
  private listaPolizas = [
    {idcliente:2, nroPoliza: 5, apellido: "weqew", nombre: "juan"},
    {idcliente:2, nroPoliza: 5, apellido: "asdf", nombre: "powp"},
    {idcliente:2, nroPoliza: 5, apellido: "erwr", nombre: "iuiu"},
    {idcliente:2, nroPoliza: 5, apellido: "jvbn", nombre: "vbc"}
  ];
  private poliza: Poliza[];
  private cliente: Cliente;

  ngOnInit() {
    this.polizaSeleccionada = null;
  }
  onSubmit(idPoliza: number, content){
    this.BuscarpolizaService.postBuscarPoliza(idPoliza);
    this.modalService.open(content, {centered: true});
  }
  loadPolizaSeleccionada(poliza){
    this.polizaSeleccionada = poliza;
  }
  cancelSeleccionarPoliza(){
    this.polizaSeleccionada = null;
    this.modalService.dismissAll();
  }
  submitSeleccionarPoliza(){
    if(this.polizaSeleccionada!=null){
      this.modalService.dismissAll();
      this.router.navigate(['/registrar-pago'])
      this.sendData();
    };
  }
  sendData(){
    this.data.changePolizaMessage(this.polizaSeleccionada);
    this.data.changeClienteMessage(this.cliente);
  }
}
