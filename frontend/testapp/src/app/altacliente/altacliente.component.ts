import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators, AbstractControl, FormBuilder } from '@angular/forms';
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
			'documento': new FormGroup({
				'tipoDocumento': new FormControl(null, Validators.required),
				'nroDocumento': new FormControl(null, Validators.required),
			}),
			'cuil': new FormControl(null, [ Validators.required, Validators.pattern('^([0-9]{2}-[0-9]{8}-[0-9]{1}|[0-9]{11})$') ]),
			'sexo': new FormControl(null, Validators.required),
			'fechaNacimiento': new FormControl(null, [ Validators.required, FechaNacimientoValidator ]),
			'direccion': new FormGroup({
				'calle': new FormControl(null, Validators.required),
				'numero': new FormControl(null, Validators.required),
				'piso': new FormControl(null),
				'departamento': new FormControl(null),
				'pais': new FormControl(null, Validators.required),
				'provincia': new FormControl(null, Validators.required),
				'localidad': new FormControl(null, Validators.required),
				'codigoPostal': new FormControl(null, [ Validators.required, Validators.pattern('^[A-Z][0-9]{3,5}[A-Z]{0,3}$') ])
			}),
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
	get tipoDocumento() { return this.altaClienteForm.get('documento.tipoDocumento'); }
	get nroDocumento() { return this.altaClienteForm.get('documento.nroDocumento'); }
	get cuil() { return this.altaClienteForm.get('cuil'); }
	get sexo() { return this.altaClienteForm.get('sexo'); }
	get fechaNacimiento() { return this.altaClienteForm.get('fechaNacimiento'); }
	get direccion() { return this.altaClienteForm.get('direccion'); }
	get calle() { return this.altaClienteForm.get('direccion.calle'); }
	get numero() { return this.altaClienteForm.get('direccion.numero'); }
	get piso() { return this.altaClienteForm.get('direccion.piso'); }
	get departamento() { return this.altaClienteForm.get('direccion.departamento'); }
	get pais() { return this.altaClienteForm.get('direccion.pais'); }
	get provincia() { return this.altaClienteForm.get('direccion.provincia'); }
	get localidad() { return this.altaClienteForm.get('direccion.localidad'); }
	get codigoPostal() { return this.altaClienteForm.get('direccion.codigoPostal'); }
	get condicionIva() { return this.altaClienteForm.get('condicionIva'); }
	get email() { return this.altaClienteForm.get('email'); }
	get estadoCivil() { return this.altaClienteForm.get('estadoCivil'); }
	get profesion() { return this.altaClienteForm.get('profesion'); }
	get anioRegistro() { return this.altaClienteForm.get('anioRegistro'); }

	onChanges(): void {
		this.altaClienteForm.get('direccion.pais').valueChanges.subscribe(idPais => {
			this.geografiaService.getProvinciasByPais(idPais).subscribe(data => {
			    this.provincias = data;
			    this.localidades = undefined;
		    });
		});

		this.altaClienteForm.get('direccion.provincia').valueChanges.subscribe(idProvincia => {
			this.geografiaService.getLocalidadesByProvincia(idProvincia).subscribe(data => {
			    this.localidades = data;
		    });
		});

		this.altaClienteForm.get('cuil').valueChanges.subscribe(cuil => {
			if(cuil.length == 11) {
				let tempValue;

		        if(cuil.length > 2) {
		            tempValue = cuil.slice(0,2) + '-' + cuil.slice(2);
		        }

		        if(cuil.length >= 11) {
		            tempValue = tempValue.slice(0,11) + '-' + tempValue.slice(11);
		        }

		        this.altaClienteForm.controls['cuil'].setValue(tempValue);
			} else if(cuil.length == 12) {
				this.altaClienteForm.controls['cuil'].setValue(cuil.split('-').join(''));
			} else if(cuil.length > 13) {
				this.altaClienteForm.controls['cuil'].setValue(cuil.substr(0, 13));
			}
		});
	}

	getListaPaises(): void {
		this.geografiaService.getPaises().subscribe(data => {
		    this.paises = data;
	    });
	}

	cancelar() {
		this.dialogService.confirm(
			'Cancelar alta de cliente',
			'¿Está seguro que desea cancelar el alta del cliente?', true, 'Sí', 'No')
			.then((confirmed) => {
				if(confirmed)
					this.router.navigate(['/']);
			});
	}

	onSubmit(f: NgForm) {
		f.value.cuil = f.value.cuil.split('-').join('');
		console.log(f.value);
	}
}
