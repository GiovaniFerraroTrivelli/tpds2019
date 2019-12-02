import { NgForm, FormGroup, FormControl, Validators, AbstractControl, FormBuilder, ValidatorFn, ValidationErrors } from '@angular/forms';
import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from "@angular/router";

import { Cliente } from '../cliente/cliente';
import { Documento } from '../cliente/documento';
import { TipoDNI } from '../enums/tipo-dni.enum';

import { DialogService } from '../dialog/dialog.service';
import { LoadingService } from '../loading/loading.service';
import { BusquedaClienteService } from './busquedacliente.service';
import { GlobalScriptsService } from '../global-scripts.service';

@Component({
  selector: 'app-buscarcliente',
  templateUrl: './buscarcliente.component.html',
  styleUrls: ['./buscarcliente.component.scss']
})

export class BuscarclienteComponent implements OnInit {

	public TipoDNI = TipoDNI;
	private resultados : Cliente[];
	private selectedClient : Cliente;

	buscarClienteForm: FormGroup;

	@Output()
	emitter = new EventEmitter<Cliente>();

	constructor(
		private busquedaClienteService: BusquedaClienteService,
		private dialogService: DialogService,
		private modalService: NgbModal,
		private loadingService: LoadingService,
		private router: Router,
		private global: GlobalScriptsService
	) { }

	ngOnInit() {
		this.buscarClienteForm = new FormGroup({
			'nroCliente': new FormControl(null, Validators.pattern('^([0-9]{10}|[0-9]{2}-[0-9]{8})$')),
			'nombre': new FormControl(null),
			'apellido': new FormControl(null),
			'documento': new FormGroup({
				'tipoDocumento': new FormControl(null),
				'nroDocumento': new FormControl(null)
			}),
			'resultadosPorPagina': new FormControl(25),
			'numeroPagina': new FormControl(1)
		}, { validators: this.atLeastOneValidator });
	}

	get nroCliente() { return this.buscarClienteForm.get('nroCliente'); }
	get nombre() { return this.buscarClienteForm.get('nombre'); }
	get apellido() { return this.buscarClienteForm.get('apellido'); }
	get documento() { return this.buscarClienteForm.get('documento'); }
	get tipoDocumento() { return this.buscarClienteForm.get('documento.tipoDocumento'); }
	get nroDocumento() { return this.buscarClienteForm.get('documento.nroDocumento'); }
	get resultadosPorPagina() { return this.buscarClienteForm.get('resultadosPorPagina'); }
	get numeroPagina() { return this.buscarClienteForm.get('numeroPagina'); }

	onBlurNroCliente(nroCliente) {
		if(nroCliente.length == 10) {
			this.buscarClienteForm.controls['nroCliente'].setValue(this.global.nroClienteFormat(nroCliente));
		}
	}

	onFocusNroCliente(nroCliente) {
		this.buscarClienteForm.controls['nroCliente'].setValue(this.global.removeHyphen(nroCliente));
	}

	getSelectedClient(){
		return this.selectedClient;
	}

	loadSelectedClient(resultado) {
		this.selectedClient = resultado;
	}

	emitCliente(){
		this.emitter.emit(this.selectedClient);
	}

	public atLeastOneValidator(form: FormGroup): ValidationErrors {
		let isAtLeastOne = false;
		
		if (form && form.controls) {
			for (const control in form.controls) {
				if (
					form.controls.hasOwnProperty(control)
					&& form.controls[control].valid
					&& form.controls[control].value
					&& control != "documento"
					&& control != "resultadosPorPagina"
					&& control != "numeroPagina"
				) {
					isAtLeastOne = true;
					break;
				}
			}
		}
		
		if(form.get('documento.nroDocumento').value !== null) {
			isAtLeastOne = true;
		}

		return isAtLeastOne ? null : { 'required': true };
	}

	onSubmit(f: NgForm, content) {
		this.loadingService.i();

		f.value.nroCliente = this.global.removeHyphen(f.value.nroCliente);
		console.log(f.value);

		this.busquedaClienteService.postClienteBusqueda(f.value).subscribe(
			data => {
			    this.resultados = data;
			    if(this.resultados.length) {
			    	this.modalService.open(content, {
			    		centered: true,
			    		size: 'lg'
			    	});
			    } else {
			    	this.dialogService.confirm(
			    		'Resultados de búsqueda',
			    		'Ningún cliente coincide con los criterios de búsqueda. ¿Desea dar de alta un nuevo cliente?', true,
			    		'Dar de alta cliente', 'Realizar otra búsqueda'
					).then((confirmed) => {
						if(confirmed)
							this.router.navigate(['/alta-cliente']);
					});
			    }
			    
			    this.loadingService.d();
		    },
		    err => {
        		this.dialogService.alert(
		    		'Ha ocurrido un error',
		    		'No se pudo realizar lo solicitado: ' + err.error.error
		    	);
			    
			    this.loadingService.d();
      		}
	    );
	}

	seleccionarCliente(modal) {
		this.emitCliente();
		this.loadSelectedClient(null);
		modal.close();
	}

	cancelarBusquedaCliente(modal) {
		this.loadSelectedClient(null);
		modal.close();
	}
}
