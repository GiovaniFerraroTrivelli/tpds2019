import { NgForm, FormGroup, FormControl, Validators, AbstractControl, FormBuilder, ValidatorFn, ValidationErrors } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from "@angular/router";

import { Poliza } from '../poliza/poliza';
import { Cliente } from '../cliente/cliente';
import { BusquedaPoliza } from './busquedapoliza';

import { BuscarpolizaService } from './buscarpoliza.service'
import { DialogService } from '../dialog/dialog.service';
import { LoadingService } from '../loading/loading.service';
import { DatashareService } from '../datashare.service'

@Component({
	selector: 'app-buscar-poliza',
	templateUrl: './buscar-poliza.component.html',
	styleUrls: ['./buscar-poliza.component.scss']
})

export class BuscarPolizaComponent implements OnInit {

	constructor(
		private BuscarpolizaService: BuscarpolizaService,
		private dialogService: DialogService,
		private modalService: NgbModal,
		private router: Router,
		private loadingService: LoadingService,
		private data: DatashareService
	) {}

	private busquedaPoliza: BusquedaPoliza;
	private polizaSeleccionada: Poliza;
	private poliza: Poliza[];
	private cliente: Cliente;
	private busquedaPolizaForm: FormGroup;

	/*private listaPolizas: Poliza[];*/
	
	private listaPolizas = [
		{ idcliente: 2, nroPoliza: 5, apellido: "weqew", nombre: "juan" },
		{ idcliente: 2, nroPoliza: 5, apellido: "asdf", nombre: "powp" },
		{ idcliente: 2, nroPoliza: 5, apellido: "erwr", nombre: "iuiu" },
		{ idcliente: 2, nroPoliza: 5, apellido: "jvbn", nombre: "vbc" }
	];

	ngOnInit() {
		this.polizaSeleccionada = null;

		this.busquedaPolizaForm = new FormGroup({
			'numeroPoliza': new FormControl(null)
		});
	}

	onSubmit(f: NgForm, content) {
	    this.loadingService.i();

		this.BuscarpolizaService.postBuscarPoliza(f.value).subscribe(
			data => {
				console.log(data);
				this.modalService.open(content, { centered: true });
			},
			err => {
			    this.loadingService.d();

				if(err.status == 403) {
					this.dialogService.alert(
						'Error al buscar una póliza',
						err.error.mensaje
					);
				} else {
					this.dialogService.alert(
						'Error al buscar una póliza',
						err.error.error
					);
				}
			}
		);
	}

	loadPolizaSeleccionada(poliza) {
		this.polizaSeleccionada = poliza;
	}

	cancelSeleccionarPoliza() {
		this.polizaSeleccionada = null;
		this.modalService.dismissAll();
	}

	submitSeleccionarPoliza() {
		if(this.polizaSeleccionada != null) {
			this.modalService.dismissAll();
			this.router.navigate(['/registrar-pago']);
			this.sendData();
		};
	}

	sendData() {
		this.data.changePolizaMessage(this.polizaSeleccionada);
		this.data.changeClienteMessage(this.cliente);
	}
}
