import { NgForm, FormGroup, FormControl, Validators, AbstractControl, FormBuilder, ValidatorFn, ValidationErrors } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import { BuscarPolizaComponent } from '../buscar-poliza/buscar-poliza.component';
import { LoadingService } from '../loading/loading.service';
import { GlobalScriptsService } from '../global-scripts.service';
import { AuthenticationService } from '../authentication/authentication.service'
import { DialogService } from '../dialog/dialog.service';
import { Router } from "@angular/router";

import { Cliente } from '../cliente/cliente';
import { Poliza } from '../poliza/poliza';
import { Cuota } from '../poliza/cuota';
import { Documento } from '../cliente/documento';
import { ResumenPoliza } from '../poliza/resumen-poliza';
import { Recibo } from './recibo';
import { RegistrarPagoService } from './registrar-pago.service';
import { RequestMontoTotal } from './requestMontoTotal';
import { RequestVuelto } from './requestVuelto';
import { ResponseVuelto } from './responseVuelto';
import { ResponseMontoTotal } from './responseMontoTotal';

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
	public page: number;
	private registrarPagoForm: FormGroup;

	private resumenPoliza: ResumenPoliza;
	private nroPoliza: string;
	private nroCliente: string;
	private index: number;
	private cuotas: Cuota[];
	private vigencia: string;
	private vencimientoCuota: string;
	private importeTotal: number;
	private recibo: Recibo;
	private requestMontoTotal: RequestMontoTotal;
	private requestVuelto: RequestVuelto;
	private responseMonto: ResponseMontoTotal;
	private responseVuelto: ResponseVuelto;
	private cuotasAPagar = [];
	private curModal: NgbModalRef;

	constructor(
		private titleService: Title,
		private modalService: NgbModal,
      	private loadingService: LoadingService,
		private global: GlobalScriptsService,
		private authentication: AuthenticationService,
		private registrarPagoService: RegistrarPagoService,
		private dialogService: DialogService,
		private router: Router,
	) { }
	
	ngOnInit() {
		this.page = 1;
		this.titleService.setTitle(this.title);
		this.requestMontoTotal = new RequestMontoTotal();
		this.requestVuelto = new RequestVuelto();
		this.requestMontoTotal.idsCuotasAPagar = [];
		this.index = 0;
		this.importeTotal = 0;

		this.registrarPagoForm = new FormGroup({
			'montoAbonado': new FormControl(null, [ (c) => this.validateMonto(c), Validators.required ])
		});
		
		this.inicializarRecibo();
	}

	get montoAbonado() { return this.registrarPagoForm.get('montoAbonado'); }

	inicializarRecibo() {
		this.recibo = new Recibo();
		this.recibo.cuotas = [];
	}

	catchSignal(event) {
		if(event) {
			this.requestMontoTotal.idPoliza = this.buscarPolizaComponent.polizaSeleccionada.idPoliza;
			this.resumenPoliza = this.buscarPolizaComponent.resumenPoliza;
			this.nroPoliza = this.buscarPolizaComponent.polizaSeleccionada.numeroPoliza;
			this.nroCliente = this.buscarPolizaComponent.polizaSeleccionada.numeroCliente;
			this.cuotas = this.resumenPoliza.cuotas.filter((a) => a.estadoCuota != 'PAGA');
			console.log(this.cuotas);
			this.page = 2;
		}
	}

	getVigenciaPoliza() {
		return this.months[ parseInt(this.resumenPoliza.finVigencia[5] + this.resumenPoliza.finVigencia[6]) ]
		+ ' ' + this.resumenPoliza.finVigencia.substr(0, 4); 
	}

	mesCuota(i) {
		return this.months[ parseInt(this.cuotas[i].fechaVencimiento[5] + this.cuotas[i].fechaVencimiento[6]) ];
	}

	anioCuota(i) {
		return this.cuotas[i].fechaVencimiento.slice(0,4);
	}

	disableCheckbox(index) {
		return (index > this.index || index < this.index - 1);
	}

	onChecked(isChecked: boolean) {
		if(isChecked) {
			this.importeTotal += Number(this.cuotas[this.index].importeFinal);
			this.index++;
		} else {
			this.index--;
			this.importeTotal -= Number(this.cuotas[this.index].importeFinal);
		}
	}
	updateCuotasAPagar(isChecked: boolean, idCuota: number){
		if(isChecked) {
			this.cuotasAPagar.push(idCuota);
		} else {
			this.cuotasAPagar.splice(this.cuotasAPagar.indexOf(idCuota), 1)
		}
	}
	
	confirmarPago(content) {
		if(!this.registrarPagoForm.valid) return;

		this.requestVuelto.montoAbonado = this.registrarPagoForm.controls['montoAbonado'].value.toString();
		this.requestVuelto.token = this.resumenPoliza.token;

		console.log(this.requestVuelto);
		this.loadingService.i();

		this.registrarPagoService.postVuelto(this.requestVuelto).subscribe(
			data => {
				this.responseVuelto = data;

				this.curModal.close();
				this.curModal = this.modalService.open(content, { backdrop: 'static', keyboard: false, centered: true });

				this.loadingService.d();
			},
			error => {
				this.dialogService.alert(
					'Error detectado',
					error.error.mensaje
				);

				this.loadingService.d();
			}
		)
	}

	validateMonto(c : AbstractControl) {
		if(c.value != null) {
			return c.value >= this.responseMonto.importeTotal ? null : {
				validateMonto: {
					valid: false
				}
			}
		} else {
			return {
				validateMonto: {
					valid: false
				}
			}
		}
		
	}

	cancel() {
		this.page = 1;
	}
	
	abonar(confirmar) {
		/*this.recibo.idPoliza = this.buscarPolizaComponent.polizaSeleccionada.idPoliza;
		let now = new Date();
		this.recibo.fecha = now.toISOString().toString();
		for (let i = 0; i < this.index; i++){
			this.recibo.cuotas.push(this.cuotas[i]);
		}
		this.recibo.operador = this.authentication.getUserName();
		this.recibo.importeTotal = this.responseMonto.importeTotal;
		console.log(this.recibo);
		this.registrarPagoService.postRecibo(this.recibo).subscribe(
			data=>{
				console.log(data);
			}
		)*/
		this.requestMontoTotal.idsCuotasAPagar = this.cuotasAPagar;
		this.requestMontoTotal.token = this.resumenPoliza.token;
		
		console.log(this.requestMontoTotal);
		this.loadingService.i();
		
		this.registrarPagoService.postCalcularImporte(this.requestMontoTotal).subscribe(
			data => {
				if(data.errores === undefined) {
					this.loadingService.d();
					console.log(data)
					this.responseMonto = data;
					this.curModal = this.modalService.open(confirmar, { centered: true });
				} else {
					this.dialogService.alert(
						'Errores detectados',
						data.errores.map(e => e.mensaje).join(". ")
					);
				}
			},
			err => {
				this.loadingService.d();

				if(err.status == 500) {
					this.dialogService.alert(
						'Error al registrar pago',
						err.error.error
					);
				} else {
					this.dialogService.alert(
						'Error al registrar pago',
						err.error.mensaje
					);
				}
			}
		)
	}

	getMontoAbonado() {
		return this.registrarPagoForm.controls['montoAbonado'].value;
	}

	getImporteFinal(i) {
		if(this.cuotas[i].importe > this.cuotas[i].importeFinal
			|| this.cuotas[i].importe < this.cuotas[i].importeFinal) {
			return this.cuotas[i].importeFinal;
		} else if (this.cuotas[i].importe == this.cuotas[i].importeFinal) return 0;
	}

	goHome() {
		this.curModal.close();
		this.router.navigate(['']);
	}
}
