import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators, AbstractControl, FormBuilder, ValidatorFn, ValidationErrors } from '@angular/forms';
import { Router } from "@angular/router";

import { TipoDNI } from '../enums/tipo-dni.enum';
import { CondicionIva } from '../enums/condicion-iva.enum';
import { Cliente } from '../cliente/cliente';

import { LoadingService } from '../loading/loading.service';
import { DialogService } from '../dialog/dialog.service';
import { ConsultarclientesService } from './consultarclientes.service';

@Component({
  selector: 'app-consultarclientes',
  templateUrl: './consultarclientes.component.html',
  styleUrls: ['./consultarclientes.component.scss']
})

export class ConsultarclientesComponent implements OnInit {
	
	public TipoDNI = TipoDNI;
	public CondicionIva = CondicionIva;

	private resultados: Cliente[];

	consultarClientesForm: FormGroup;

	constructor(
		private consultarClientesService: ConsultarclientesService,
		private titleService: Title,
		private dialogService: DialogService,
		private loadingService: LoadingService,
		private router: Router
	) { }

	public title = "Consultar clientes";

	ngOnInit() {
		this.titleService.setTitle(this.title);

		this.consultarClientesForm = new FormGroup({
			'idCliente': new FormControl(null, [ Validators.min(0), Validators.pattern('^[0-9]*$') ]),
			'nombre': new FormControl(null),
			'apellido': new FormControl(null),
			'documento': new FormGroup({
				'tipoDocumento': new FormControl(null),
				'nroDocumento': new FormControl(null),
			}),
			'condicionIva': new FormControl(null),
			'results': new FormControl(25, [ Validators.required, Validators.min(1), Validators.max(500), Validators.pattern('^[0-9]*$') ]),
		}, { validators: this.atLeastOneValidator });

		this.resultados = [];
	}

	get idCliente() { return this.consultarClientesForm.get('idCliente'); }
	get nombre() { return this.consultarClientesForm.get('nombre'); }
	get apellido() { return this.consultarClientesForm.get('apellido'); }
	get documento() { return this.consultarClientesForm.get('documento'); }
	get tipoDocumento() { return this.consultarClientesForm.get('documento.tipoDocumento'); }
	get nroDocumento() { return this.consultarClientesForm.get('documento.nroDocumento'); }
	get condicionIva() { return this.consultarClientesForm.get('condicionIva'); }
	get results() { return this.consultarClientesForm.get('results'); }

	public atLeastOneValidator(form: FormGroup): ValidationErrors {
		let isAtLeastOne = false;
		
		if (form && form.controls) {
			for (const control in form.controls) {
				if (form.controls.hasOwnProperty(control) && form.controls[control].valid && form.controls[control].value && control != "documento" && control != "results") {
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

	cuilFormat(cuil) {
		return cuil.substr(0, 2) + '-' + cuil.substr(2, 8) + '-' + cuil.substr(10);
	}

	onSubmit(f: NgForm, content) {
		this.loadingService.i();

		this.consultarClientesService.postClienteBusqueda(f.value).subscribe(
			data => {
			    this.resultados = data;

			    if(this.resultados.length) {
			    	console.log(this.resultados);
			    } else {
			    	this.dialogService.alert(
			    		'Resultados de búsqueda',
			    		'Ningún cliente coincide con los criterios de búsqueda. Intente nuevamente.'
			    	);
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
}
