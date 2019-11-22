import { Component, OnInit } from '@angular/core';
import { BuscarpolizaService } from './buscarpoliza.service'

@Component({
  selector: 'app-buscar-poliza',
  templateUrl: './buscar-poliza.component.html',
  styleUrls: ['./buscar-poliza.component.scss']
})
export class BuscarPolizaComponent implements OnInit {

  constructor(private BuscarpolizaService: BuscarpolizaService) { }
  private idPoliza: number;

  ngOnInit() {
  }
  onSubmit(idPoliza: number){
    this.BuscarpolizaService.postBuscarPoliza(idPoliza);
    console.log(this.idPoliza);
  }
}
