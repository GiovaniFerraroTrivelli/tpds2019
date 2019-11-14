import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { Router } from "@angular/router";

import { TipoDNI } from '../enums/tipo-dni.enum';
import { CondicionIva } from '../enums/condicion-iva.enum';
import { Cliente } from '../cliente/cliente';

import { LoadingService } from '../loading/loading.service';
import { DialogService } from '../dialog/dialog.service';

@Component({
  selector: 'app-consultarclientes',
  templateUrl: './consultarclientes.component.html',
  styleUrls: ['./consultarclientes.component.scss']
})

export class ConsultarclientesComponent implements OnInit {
	
	public TipoDNI = TipoDNI;
	public CondicionIva = CondicionIva;

	private results: Cliente[];

	consultarClientesForm: FormGroup;

	constructor(
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
			'tipoDocumento': new FormControl(null),
			'numeroDocumento': new FormControl(null),
			'condicionIva': new FormControl(null)
		});

		this.results = [];
	}

	isValidForm(f: NgForm)
	{
		for(let prop in f.value) {
			if(prop != "tipoDocumento" && f.value[prop] !== null && f.value[prop] !== '') {
				return true;
			}
		}

		return false;
	}

	onSubmit(f : NgForm) {
		console.log(f.value);

		this.results = [ new Cliente() ];

		console.log(this.results);
	}

}
