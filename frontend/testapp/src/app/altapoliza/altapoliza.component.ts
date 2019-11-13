import { Component, OnInit, ViewChild } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NgForm, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { Router } from "@angular/router";

import { HijosComponent } from '../hijos/hijos.component';
import { TipocoberturaComponent } from '../tipocobertura/tipocobertura.component';

import { GeografiaService } from '../geografia/geografia.service';
import { ModelosService } from '../modelos/modelos.service';
import { AltaPolizaService } from './altapoliza.service';
import { LoadingService } from '../loading/loading.service';
import { DialogService } from '../dialog/dialog.service';

import { Hijo } from '../hijos/hijo';
import { Provincia } from '../geografia/provincia';
import { Localidad } from '../geografia/localidad';
import { Marca } from '../modelos/marca';
import { Modelo } from '../modelos/modelo';
import { Cliente } from '../cliente/cliente';
import { Documento } from '../cliente/documento';
import { Direccion } from '../cliente/direccion';
import { Poliza } from '../poliza/poliza';
import { TipoCobertura } from 'TipoCobertura';

@Component({
	selector: 'app-altapoliza',
	templateUrl: './altapoliza.component.html',
	styleUrls: ['./altapoliza.component.scss']
})

export class AltapolizaComponent implements OnInit {

	@ViewChild(HijosComponent, { static: false }) childComp: HijosComponent;

	public title = "Alta de póliza";

	private provincias : Provincia[];
	private localidades : Localidad[];
	private anios : number[];
	private marcas : Marca[];
	private modelos : Modelo[];
	private cliente : Cliente;
	private coberturasDisponibles: TipoCobertura[];
	private nextStep: boolean;
	private polizaValues: Poliza;
	altaPolizaForm: FormGroup;
	private marcaSeleccionada: String;
	private modeloSeleccionado: String;

	constructor(
		private titleService: Title,
		private dialogService: DialogService,
		private geografiaService: GeografiaService,
		private modelosService: ModelosService,
		private altaPolizaService: AltaPolizaService,
		private loadingService: LoadingService,
		private router: Router
	) { 
	}

	ngOnInit() {
		this.cliente = new Cliente();
		this.cliente.direccion = new Direccion();
		this.cliente.direccion.localidad = new Localidad();
		this.cliente.documento = new Documento();

		this.altaPolizaForm = new FormGroup({
			'idCliente': new FormControl(null, Validators.required),
			'nombre': new FormControl({ value: '', disabled: true }),
			'apellido': new FormControl({ value: '', disabled: true }),
			'nroDocumento': new FormControl({ value: '', disabled: true }),
			'direccion': new FormControl({ value: '', disabled: true }),
			'marca': new FormControl(null, Validators.required),
			'modelo': new FormControl(null, Validators.required),
			'anio': new FormControl(null, Validators.required),
			'motor': new FormControl(null, [ Validators.required, Validators.pattern('^([A-Z0-9]*)$') ]),
			'chasis': new FormControl(null, [ Validators.required, Validators.pattern('^([A-Z0-9]{17})$') ]),
			'patente': new FormControl(null, Validators.pattern('^(|[A-Z]{3}[0-9]{3}|[A-Z]{2}[0-9]{3}[A-Z]{2})$')),
			'provincia': new FormControl(null, Validators.required),
			'localidad': new FormControl(null, Validators.required),
			'seGuardaEnGarage': new FormControl(null),
			'poseeAlarma': new FormControl(null),
			'poseeRastreoVehicular': new FormControl(null),
			'poseeTuercasAntirrobo': new FormControl(null),
			'siniestros': new FormControl(null, Validators.required),
			'kmAnio': new FormControl(null, [ Validators.min(0), Validators.required ]),
			'sumaAsegurada': new FormControl({ value: 0, disabled: true })
		});

		// seteamos false para que no esté en null
		this.altaPolizaForm.controls['seGuardaEnGarage'].setValue(false);
		this.altaPolizaForm.controls['poseeAlarma'].setValue(false);
		this.altaPolizaForm.controls['poseeRastreoVehicular'].setValue(false);
		this.altaPolizaForm.controls['poseeTuercasAntirrobo'].setValue(false);

		this.onChanges();

		this.titleService.setTitle(this.title);
		this.getListaProvincias();
		this.getListaMarcas();
	}

	get idCliente() { return this.altaPolizaForm.get('idCliente'); }
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
	get siniestros() { return this.altaPolizaForm.get('siniestros'); }
	get kmAnio() { return this.altaPolizaForm.get('kmAnio'); }

	onChanges(): void {
		this.altaPolizaForm.get('marca').valueChanges.subscribe(idMarca => {
			this.modelosService.getModelosByMarca(idMarca).subscribe(data => {
			    this.modelos = data;
			    this.anios = undefined;
		    });
		});

		this.altaPolizaForm.get('provincia').valueChanges.subscribe(idProvincia => {
			this.geografiaService.getLocalidadesByProvincia(idProvincia).subscribe(data => {
			    this.localidades = data;
		    });
		});

		this.altaPolizaForm.get('modelo').valueChanges.subscribe(idModelo => {
			this.modelosService.getAniosByModelo(idModelo).subscribe(data => {
			    this.anios = data;
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

		console.info(f.value);

		this.loadingService.i();

		this.altaPolizaService.postValidarDatos1(f.value).subscribe(data => {
		    this.loadingService.d();

		    if(data.errores.length) {
		    	this.dialogService.alert(
		    		'Errores detectados',
		    		data.errores.map(e => e.mensaje).join(". ")
		    	);
		    } else {
		    	this.polizaValues = f.value;
		    	this.coberturasDisponibles = data.coberturasDisponibles;
				this.nextStep = true;
		    }
		});
	}

	convertToUppercase(thisField) {
		this.altaPolizaForm.controls[thisField].setValue(this.altaPolizaForm.get(thisField).value.toUpperCase());
	}

	processCliente(cliente) {
		this.cliente = cliente;

		this.altaPolizaForm.controls['idCliente'].setValue(this.cliente.idCliente);
		this.altaPolizaForm.controls['nombre'].setValue(this.cliente.nombre);
		this.altaPolizaForm.controls['apellido'].setValue(this.cliente.apellido);
		this.altaPolizaForm.controls['nroDocumento'].setValue(this.cliente.documento.nroDocumento);
		this.altaPolizaForm.controls['direccion'].setValue(this.cliente.direccion.calle + ' ' + this.cliente.direccion.numero);
	}

	cancelar() {
		this.dialogService.confirm(
			'Cancelar alta de póliza',
			'¿Está seguro que desea cancelar el alta de la póliza?', true, 'Sí', 'No')
			.then((confirmed) => {
				if(confirmed)
					this.router.navigate(['/']);
			});
	}

	prevStep() {
		this.polizaValues.idCobertura = undefined;
		this.polizaValues.fechaVigencia = undefined;
		this.polizaValues.modalidadPago = undefined;
		
		this.nextStep = false;
	}
}
