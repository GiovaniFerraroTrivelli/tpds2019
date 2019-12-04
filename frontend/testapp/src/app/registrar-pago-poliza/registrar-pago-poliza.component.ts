import { NgForm, FormGroup, FormControl, Validators, AbstractControl, FormBuilder, ValidatorFn, ValidationErrors } from '@angular/forms';
import { Component, OnInit, ViewChild} from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { BuscarPolizaComponent } from '../buscar-poliza/buscar-poliza.component';
import { LoadingService } from '../loading/loading.service';
import { GlobalScriptsService } from '../global-scripts.service';

import { Cliente } from '../cliente/cliente';
import { Poliza } from '../poliza/poliza';
import { Cuota } from '../poliza/cuota';
import { Documento } from '../cliente/documento';
import { ResumenPoliza } from '../poliza/resumen-poliza';

@Component({
  selector: 'app-registrar-pago-poliza',
  templateUrl: './registrar-pago-poliza.component.html',
  styleUrls: ['./registrar-pago-poliza.component.scss']
})
export class RegistrarPagoPolizaComponent implements OnInit {

	@ViewChild(BuscarPolizaComponent, { static: false }) buscarPolizaComponent: BuscarPolizaComponent;

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

	public title = "Registrar pago de póliza";
	public isCollapsed: boolean;
	private registrarPagoForm: FormGroup;

	private resumenPoliza: ResumenPoliza;
	private nroPoliza: string;
	private nroCliente: string;
	private index: number;
	private cuotas: Cuota[];
	private vigencia: string;
	private vencimientoCuota: string;
	private importeTotal: number;

	constructor(
		private titleService: Title,
		private modalService: NgbModal,
      	private loadingService: LoadingService,
		private global: GlobalScriptsService
	) { }

	ngOnInit() {
		this.isCollapsed = false;
		this.titleService.setTitle(this.title);

		this.index = 0;
		this.importeTotal = 0;

		this.registrarPagoForm = new FormGroup({
			'montoAbonado': new FormControl(null, [Validators.required])
		});
	}

	catchSignal(event) {
		if(event) {
			this.resumenPoliza = this.buscarPolizaComponent.resumenPoliza;
			this.nroPoliza = this.buscarPolizaComponent.polizaSeleccionada.numeroPoliza;
			this.nroCliente = this.buscarPolizaComponent.polizaSeleccionada.numeroCliente;
			this.cuotas = this.resumenPoliza.cuotas;

			this.isCollapsed = true;
		}
	}

	setVigencia() {
		return this.months[ parseInt(this.resumenPoliza.finVigencia[5] + this.resumenPoliza.finVigencia[6]) ]
		+ ' ' + this.resumenPoliza.finVigencia.substr(0, 4); 
	}

	getVencimientoCuota(i) {
		return this.cuotas[i].fechaVencimiento.slice(8,10) + '/' 
		+ this.cuotas[i].fechaVencimiento.slice(5,7) + '/' 
		+ this.cuotas[i].fechaVencimiento.slice(0,4);
	}

	mesCuota(i) {
		return this.months[ parseInt(this.cuotas[i].fechaVencimiento[5] + this.cuotas[i].fechaVencimiento[6]) ];
	}

	disableCheckbox(index) {
		return (index > this.index || index < this.index - 1);
	}

	onChecked(isChecked: boolean) {
		if(isChecked) {
			this.importeTotal += Number(this.cuotas[this.index].importe);
			this.index++;
		} else {
			this.index--;
			this.importeTotal -= Number(this.cuotas[this.index].importe);
		}
	}

	openModal(content) {
		this.modalService.open(content, { centered: true });
	}

	checkMontoAbonado() {
		if(this.importeTotal > this.registrarPagoForm.controls['montoAbonado'].value) {
			this.registrarPagoForm.controls.montoAbonado.setErrors({ 'incorrect': true });
		}
	}
}
