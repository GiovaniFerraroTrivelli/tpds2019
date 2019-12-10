import { NgForm, FormGroup, FormControl, Validators, AbstractControl, FormBuilder, ValidatorFn, ValidationErrors } from '@angular/forms';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from "@angular/router";

import { Poliza } from '../poliza/poliza';
import { Cliente } from '../cliente/cliente';
import { BusquedaPoliza } from './busquedapoliza';

import { BuscarpolizaService } from './buscarpoliza.service'
import { DialogService } from '../dialog/dialog.service';
import { LoadingService } from '../loading/loading.service';
import { DatashareService } from '../datashare.service'
import { PolizasRta } from './polizaRespuesta';
import { respuestaBuscarPoliza } from './respuestaBuscarPoliza';
import { ResumenPoliza } from '../poliza/resumen-poliza';
import { GlobalScriptsService } from '../global-scripts.service';

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
		private data: DatashareService,
		private global: GlobalScriptsService
	) {}

	private busquedaPoliza: BusquedaPoliza;
	polizaSeleccionada: PolizasRta;
	private respuestaPolizas: respuestaBuscarPoliza;
	busquedaPolizaForm: FormGroup;
	private listaPolizas: PolizasRta[];
	resumenPoliza: ResumenPoliza;
	@Output() signal = new EventEmitter<boolean>();

	ngOnInit() {
		this.polizaSeleccionada = null;
		this.busquedaPolizaForm = new FormGroup({
			'numeroPoliza': new FormControl(null, [ Validators.required, Validators.pattern('^([0-9]{13})$') ])
    	});
    	
    	this.onChanges();
  	}
  
  	get numeroPoliza() { return this.busquedaPolizaForm.get('numeroPoliza'); }

	onChanges(): void {
		this.busquedaPolizaForm.get('numeroPoliza').valueChanges.subscribe(
			numeroPoliza => {
				if(numeroPoliza.length > 13){
					this.busquedaPolizaForm.controls['numeroPoliza'].setValue(numeroPoliza.substr(0,13));
				}
			}
		);
	}
  
	onSubmit(f: NgForm, content) {
	    this.loadingService.i();

		this.BuscarpolizaService.postBuscarPoliza(f.value).subscribe(
			data => {
	      		this.loadingService.d();

	      		if(!data.polizas.length) {
	      			this.dialogService.alert(
						'Sin resultados',
						'No se encontraron pólizas con el número de póliza consultado.'
					);
	      		} else {
					this.respuestaPolizas = data;
					this.listaPolizas = this.respuestaPolizas.polizas;

	      			this.modalService.open(content, { centered: true, size: 'lg' });
	      		}
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

	loadPolizaSeleccionada(poliza : PolizasRta) {
		this.polizaSeleccionada = poliza;
	}

	cancelSeleccionarPoliza() {
		this.polizaSeleccionada = null;
		this.modalService.dismissAll();
	}

	submitSeleccionarPoliza() {
		if(this.polizaSeleccionada == null) return;

		this.BuscarpolizaService.getPolizaSeleccionada(this.polizaSeleccionada.idPoliza).subscribe(
			data=>{
				this.resumenPoliza = data;
				this.signal.emit(true);
			}
		)
		this.modalService.dismissAll();
    }

    cancelarBusqueda() {
		this.dialogService.confirm(
			'Cancelar búsqueda de póliza',
			'¿Está seguro que desea cancelar la búsqueda de la póliza?', true, 'Sí', 'No')
			.then((confirmed) => {
				if(confirmed)
					this.router.navigate(['/']);
		}, () => {});
    }
}
