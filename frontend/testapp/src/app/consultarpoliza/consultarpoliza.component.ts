import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NgForm, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';

import { LoadingService } from '../loading/loading.service';
import { ModelosService } from '../modelos/modelos.service';

import { EstadoPoliza } from '../enums/estado-poliza.enum';
import { Poliza } from '../poliza/poliza';
import { Marca } from '../modelos/marca';
import { Modelo } from '../modelos/modelo';

@Component({
	selector: 'app-consultarpoliza',
	templateUrl: './consultarpoliza.component.html',
	styleUrls: ['./consultarpoliza.component.scss']
})
export class ConsultarpolizaComponent implements OnInit {

	consultarPolizaForm: FormGroup;

	private results: Poliza[];
	private marcas : Marca[];
	private modelos : Modelo[];
	public EstadoPoliza = EstadoPoliza;

	constructor(
		private loadingService: LoadingService,
		private modelosService: ModelosService,
		private titleService: Title
	) {}

	public title = "Consultar póliza";

	ngOnInit()
	{
		this.titleService.setTitle(this.title);

		this.consultarPolizaForm = new FormGroup({
			'idPoliza': new FormControl(null, [ Validators.min(0), Validators.pattern('^[0-9]*$') ]),
			'apellido': new FormControl(null),
			'marca': new FormControl(null),
			'modelo': new FormControl(null),
			'patente': new FormControl(null, Validators.pattern('^(|[A-Z]{3}[0-9]{3}|[A-Z]{2}[0-9]{3}[A-Z]{2})$')),
			'inicioVigencia': new FormControl(null),
			'finVigencia': new FormControl(null),
			'estadoPoliza': new FormControl(null)
		});

		this.onChanges();

		this.results = [];
		this.getListaMarcas();
	}

	get idPoliza() { return this.consultarPolizaForm.get('idPoliza'); }
	get apellido() { return this.consultarPolizaForm.get('apellido'); }
	get marca() { return this.consultarPolizaForm.get('marca'); }
	get modelo() { return this.consultarPolizaForm.get('modelo'); }
	get patente() { return this.consultarPolizaForm.get('patente'); }
	get inicioVigencia() { return this.consultarPolizaForm.get('inicioVigencia'); }
	get finVigencia() { return this.consultarPolizaForm.get('finVigencia'); }
	get estadoPoliza() { return this.consultarPolizaForm.get('estadoPoliza'); }

	onChanges(): void {
		this.consultarPolizaForm.get('marca').valueChanges.subscribe(idMarca => {
			this.modelosService.getModelosByMarca(idMarca).subscribe(data => {
			    this.modelos = data;
		    });
		});
	}

	getListaMarcas(): void {
		this.modelosService.getMarcas().subscribe(data => {
		    this.marcas = data;
	    });
	}

	isValidForm(f: NgForm)
	{
		for(let prop in f.value) {
			if(f.value[prop] !== null && f.value[prop] !== '') {
				return true;
			}
		}

		return false;
	}

	convertToUppercase(thisField) {
		this.consultarPolizaForm.controls[thisField].setValue(this.consultarPolizaForm.get(thisField).value.toUpperCase());
	}

	onSubmit(f : NgForm) {
		console.log(f.value);

		this.results = [ new Poliza() ];

		console.log(this.results);
	}
}
