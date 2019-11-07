import { Component, OnInit, ViewChild } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NgForm, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';

import { HijosComponent } from '../hijos/hijos.component';
import { TipocoberturaComponent } from '../tipocobertura/tipocobertura.component';

import { GeografiaService } from '../geografia/geografia.service';
import { ModelosService } from '../modelos/modelos.service';
import { AltaPolizaService } from './altapoliza.service';
import { LoadingService } from '../loading/loading.service';

import { Hijo } from '../hijos/hijo';
import { Provincia } from '../geografia/provincia';
import { Localidad } from '../geografia/localidad';
import { Marca } from '../modelos/marca';
import { Modelo } from '../modelos/modelo';
import { Cliente } from '../cliente/cliente';
import { Documento } from '../cliente/documento';
import { Direccion } from '../cliente/direccion';
import { TipoCobertura } from 'TipoCobertura';

@Component({
	selector: 'app-altapoliza',
	templateUrl: './altapoliza.component.html',
	styleUrls: ['./altapoliza.component.scss']
})

export class AltapolizaComponent implements OnInit {

	@ViewChild(HijosComponent, { static: false }) childComp: HijosComponent;

	private provincias : Provincia[];
	private localidades : Localidad[];
	private marcas : Marca[];
	private modelos : Modelo[];
	private cliente : Cliente;
	private coberturasDisponibles: TipoCobertura[];
	private nextStep: boolean;
	private polizaValues: Object;
	altaPolizaForm: FormGroup;

	constructor(
		private titleService: Title,
		private geografiaService: GeografiaService,
		private modelosService: ModelosService,
		private altaPolizaService: AltaPolizaService,
		private loadingService: LoadingService
	) { 
	}

	ngOnInit() {
		this.cliente = new Cliente();
		this.cliente.direccion = new Direccion();
		this.cliente.direccion.localidad = new Localidad();
		this.cliente.documento = new Documento();


		this.altaPolizaForm = new FormGroup({
			'idcliente': new FormControl(null, Validators.required),
			'nombre': new FormControl({ value: '', disabled: true }),
			'apellido': new FormControl({ value: '', disabled: true }),
			'nroDocumento': new FormControl({ value: '', disabled: true }),
			'direccion': new FormControl({ value: '', disabled: true }),
			'marca': new FormControl(null, Validators.required),
			'modelo': new FormControl(null, Validators.required),
			'anio': new FormControl(null, Validators.required),
			'motor': new FormControl(null, Validators.required),
			'chasis': new FormControl(null, [ Validators.required, Validators.pattern('^([A-Z0-9]{17})$') ]),
			'patente': new FormControl(null, Validators.pattern('^(|[A-Z]{3}[0-9]{3}|[A-Z]{2}[0-9]{3}[A-Z]{2})$')),
			'provincia': new FormControl(null, Validators.required),
			'localidad': new FormControl(null, Validators.required),
			'seGuardaEnGarage': new FormControl(null),
			'poseeAlarma': new FormControl(null),
			'poseeRastreoVehicular': new FormControl(null),
			'poseeTuercasAntirrobo': new FormControl(null),
			'nroSiniestros': new FormControl(null, Validators.required),
			'sumaAsegurada': new FormControl({ value: 0, disabled: true })
		});

		// seteamos false para que no esté en null
		this.altaPolizaForm.controls['seGuardaEnGarage'].setValue(false);
		this.altaPolizaForm.controls['poseeAlarma'].setValue(false);
		this.altaPolizaForm.controls['poseeRastreoVehicular'].setValue(false);
		this.altaPolizaForm.controls['poseeTuercasAntirrobo'].setValue(false);

		this.onChanges();

		this.titleService.setTitle("Dar de alta póliza");
		this.getListaProvincias();
		this.getListaMarcas();
	}

	get idcliente() { return this.altaPolizaForm.get('idcliente'); }
	get nombre() { return this.altaPolizaForm.get('nombre'); }
	get apellido() { return this.altaPolizaForm.get('apellido'); }
	get nroDocumento() { return this.altaPolizaForm.get('nroDocumento'); }
	get direccion() { return this.altaPolizaForm.get('direccion'); }
	get marca() { return this.altaPolizaForm.get('marca'); }
	get modelo() { return this.altaPolizaForm.get('modelo'); }
	get anio() { return this.altaPolizaForm.get('anio'); }
	get motor() { return this.altaPolizaForm.get('motor'); }
	get chasis() { return this.altaPolizaForm.get('chasis'); }
	get patente() { return this.altaPolizaForm.get('patente'); }
	get provincia() { return this.altaPolizaForm.get('provincia'); }
	get localidad() { return this.altaPolizaForm.get('localidad'); }
	get seGuardaEnGarage() { return this.altaPolizaForm.get('seGuardaEnGarage'); }
	get poseeAlarma() { return this.altaPolizaForm.get('poseeAlarma'); }
	get poseeRastreoVehicular() { return this.altaPolizaForm.get('poseeRastreoVehicular'); }
	get poseeTuercasAntirrobo() { return this.altaPolizaForm.get('poseeTuercasAntirrobo'); }

	onChanges(): void {
		this.altaPolizaForm.get('marca').valueChanges.subscribe(idMarca => {
			this.modelosService.getModelosByMarca(idMarca).subscribe(data => {
			    this.modelos = data;
		    });
		});

		this.altaPolizaForm.get('provincia').valueChanges.subscribe(idProvincia => {
			this.geografiaService.getLocalidadesByProvincia(idProvincia).subscribe(data => {
			    this.localidades = data;
		    });
		});
	}

	getListaProvincias(): void {
		this.geografiaService.getProvinciasByPais(1).subscribe(data => {
		    this.provincias = data;
	    });
	}

	getListaMarcas(): void {
		this.modelosService.getMarcas().subscribe(data => {
		    this.marcas = data;
	    });
	}

	onSubmit(f: NgForm) {
		f.value.hijos = this.childComp.hijos;
		console.log(typeof(f.value));

		this.loadingService.i();

		this.altaPolizaService.postValidarDatos(f.value).subscribe(data => {
		    this.loadingService.d();
			this.coberturasDisponibles = data.coberturasDisponibles;
			this.polizaValues = f.value;
			//console.log(data.errores.length);
		    //console.log(data.coberturasDisponibles.length);

		    if(data.errores.length) {
		    	// aca apareceria un cartelito con todos los errores que tiene
		    } else {
		    	// data.coberturasDisponibles posee un array de TipoCoberturas.
		    	this.nextStep = true;
		    }
	    });
	}

	convertToUppercase(thisField) {
		this.altaPolizaForm.controls[thisField].setValue(this.altaPolizaForm.get(thisField).value.toUpperCase());
	}

	processCliente(cliente) {
		this.cliente = cliente;

		this.altaPolizaForm.controls['idcliente'].setValue(this.cliente.idCliente);
		this.altaPolizaForm.controls['nombre'].setValue(this.cliente.nombre);
		this.altaPolizaForm.controls['apellido'].setValue(this.cliente.apellido);
		this.altaPolizaForm.controls['nroDocumento'].setValue(this.cliente.documento.nroDocumento);
		this.altaPolizaForm.controls['direccion'].setValue(this.cliente.direccion.calle + ' ' + this.cliente.direccion.numero);
	}

	prevStep() {
		this.nextStep = false;
	}
}
