import { Component, OnInit, ViewChild} from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ResumenPoliza } from '../poliza/resumen-poliza';
import { BuscarPolizaComponent } from '../buscar-poliza/buscar-poliza.component';

@Component({
  selector: 'app-registrar-pago-poliza',
  templateUrl: './registrar-pago-poliza.component.html',
  styleUrls: ['./registrar-pago-poliza.component.scss']
})
export class RegistrarPagoPolizaComponent implements OnInit {

	@ViewChild(BuscarPolizaComponent, { static: false }) buscarPolizaComponent: BuscarPolizaComponent;

	public title = "Registrar pago de póliza";
	public isCollapsed: boolean;
	private resumenPoliza: ResumenPoliza;
	private nroPoliza: string;
	private nroCliente: string;

	constructor(
		private titleService: Title,
		private modalService: NgbModal,
	) { }

	ngOnInit() {
		this.isCollapsed = false;
		this.titleService.setTitle(this.title);
	}
	catchSignal(event){
		if(event){
			this.resumenPoliza = this.buscarPolizaComponent.resumenPoliza;
			this.nroPoliza = this.buscarPolizaComponent.polizaSeleccionada.numeroPoliza;
			this.nroCliente = this.buscarPolizaComponent.polizaSeleccionada.numeroCliente;
		}
		this.isCollapsed = true;
	}
}
