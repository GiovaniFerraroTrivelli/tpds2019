import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { Router } from "@angular/router";

import { GeografiaService } from '../geografia/geografia.service';
import { LoadingService } from '../loading/loading.service';
import { DialogService } from '../dialog/dialog.service';

import { CondicionIva } from '../enums/condicion-iva.enum';
import { Sexo } from '../enums/sexo.enum';
import { EstadoCivil } from '../enums/estado-civil.enum';
import { TipoDNI } from '../enums/tipo-dni.enum';
import { Pais } from '../geografia/pais';
import { Provincia } from '../geografia/provincia';
import { Localidad } from '../geografia/localidad';

import { FechaNacimientoValidator } from './fechanacimiento.validator';

@Component({
  selector: 'app-altacliente',
  templateUrl: './altacliente.component.html',
  styleUrls: ['./altacliente.component.scss']
})
export class AltaclienteComponent implements OnInit {

	constructor(
		private titleService: Title,
		private dialogService: DialogService,
		private geografiaService: GeografiaService,
		private loadingService: LoadingService,
		private router: Router
	) { }

	public title = "Alta de cliente";

	public CondicionIva = CondicionIva;
	public Sexo = Sexo;
	public EstadoCivil = EstadoCivil;
	public TipoDNI = TipoDNI;

	private paises : Pais[];
	private provincias : Provincia[];
	private localidades : Localidad[];

	altaClienteForm: FormGroup;

	ngOnInit() {
		this.titleService.setTitle(this.title);

		this.altaClienteForm = new FormGroup({
			'idCliente': new FormControl({ value: '', disabled: true }),
			'nombre': new FormControl(null, Validators.required),
			'apellido': new FormControl(null, Validators.required),
			'tipoDocumento': new FormControl(null, Validators.required),
			'nroDocumento': new FormControl(null, Validators.required),
			'cuil': new FormControl(null, Validators.required),
			'sexo': new FormControl(null, Validators.required),
			'fechaNacimiento': new FormControl(null, [ Validators.required, FechaNacimientoValidator ]),
			'calle': new FormControl(null, Validators.required),
			'numero': new FormControl(null, Validators.required),
			'piso': new FormControl(null),
			'departamento': new FormControl(null),
			'pais': new FormControl(null, Validators.required),
			'provincia': new FormControl(null, Validators.required),
			'localidad': new FormControl(null, Validators.required),
			'codigoPostal': new FormControl(null, Validators.required),
			'condicionIva': new FormControl(null, Validators.required),
			'email': new FormControl(null, [ Validators.required, Validators.pattern('^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$') ]),
			'estadoCivil': new FormControl(null, Validators.required),
			'profesion': new FormControl(null, Validators.required),
			'anioRegistro': new FormControl(null, Validators.required)
		});

		this.onChanges();

		this.getListaPaises();
	}

	get idCliente() { return this.altaClienteForm.get('idCliente'); }
	get nombre() { return this.altaClienteForm.get('nombre'); }
	get apellido() { return this.altaClienteForm.get('apellido'); }
	get tipoDocumento() { return this.altaClienteForm.get('tipoDocumento'); }
	get nroDocumento() { return this.altaClienteForm.get('nroDocumento'); }
	get cuil() { return this.altaClienteForm.get('cuil'); }
	get sexo() { return this.altaClienteForm.get('sexo'); }
	get fechaNacimiento() { return this.altaClienteForm.get('fechaNacimiento'); }
	get calle() { return this.altaClienteForm.get('calle'); }
	get numero() { return this.altaClienteForm.get('numero'); }
	get piso() { return this.altaClienteForm.get('piso'); }
	get departamento() { return this.altaClienteForm.get('departamento'); }
	get pais() { return this.altaClienteForm.get('pais'); }
	get provincia() { return this.altaClienteForm.get('provincia'); }
	get localidad() { return this.altaClienteForm.get('localidad'); }
	get codigoPostal() { return this.altaClienteForm.get('codigoPostal'); }
	get condicionIva() { return this.altaClienteForm.get('condicionIva'); }
	get email() { return this.altaClienteForm.get('email'); }
	get estadoCivil() { return this.altaClienteForm.get('estadoCivil'); }
	get profesion() { return this.altaClienteForm.get('profesion'); }
	get anioRegistro() { return this.altaClienteForm.get('anioRegistro'); }

	onChanges(): void {
		this.altaClienteForm.get('pais').valueChanges.subscribe(idPais => {
			this.geografiaService.getProvinciasByPais(idPais).subscribe(data => {
			    this.provincias = data;
			    this.localidades = undefined;
		    });
		});

		this.altaClienteForm.get('provincia').valueChanges.subscribe(idProvincia => {
			this.geografiaService.getLocalidadesByProvincia(idProvincia).subscribe(data => {
			    this.localidades = data;
		    });
		});
	}

	getListaPaises(): void {
		this.geografiaService.getPaises().subscribe(data => {
		    this.paises = data;
	    });
	}

	onSubmit(f: NgForm) {
		console.log("aca abajo");
		let formJSON = f.value;
		console.log(f.value);
	}
}
