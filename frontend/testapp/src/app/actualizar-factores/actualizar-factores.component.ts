import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';

import { TipoCobertura } from '../tipocobertura/tipocobertura.enum';
import { MedidasSeguridad } from '../enums/medidas-seguridad.enum';
import { Provincia } from '../geografia/provincia';
import { Localidad } from '../geografia/localidad';
import { Marca } from '../modelos/marca';
import { Modelo } from '../modelos/modelo';

import { GeografiaService } from '../geografia/geografia.service';
import { ModelosService } from '../modelos/modelos.service';
import { LoadingService } from '../loading/loading.service';

@Component({
  selector: 'app-actualizar-factores',
  templateUrl: './actualizar-factores.component.html',
  styleUrls: ['./actualizar-factores.component.scss']
})
export class ActualizarFactoresComponent implements OnInit {

	public title = "Actualizar factores de características";

	public TipoCobertura = TipoCobertura;
	public MedidasSeguridad = MedidasSeguridad;

	private provincias : Provincia[];
	private localidades : Localidad[];
	private marcas : Marca[];
	private modelos : Modelo[];

	estadisticasForm: FormGroup;
	domiciliosForm: FormGroup;

	constructor(
		private titleService: Title,
		private geografiaService: GeografiaService,
		private modelosService: ModelosService,
		private loadingService: LoadingService,
	) { }

	ngOnInit() {
		this.titleService.setTitle(this.title);

		this.estadisticasForm = new FormGroup({
			'marca': new FormControl(null),
			'modelo': new FormControl(null)
		});

		this.domiciliosForm = new FormGroup({
			'provincia': new FormControl(null),
			'localidad': new FormControl(null)
		});

		this.onChanges();

		this.getListaProvincias();
		this.getListaMarcas();
	}

	onChanges(): void {
		this.estadisticasForm.get('marca').valueChanges.subscribe(idMarca => {
			this.modelosService.getModelosByMarca(idMarca).subscribe(data => {
			    this.modelos = data;
		    });
		});

		this.domiciliosForm.get('provincia').valueChanges.subscribe(idProvincia => {
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

	onSubmit(f : NgForm) {
		console.log(f.value);
	}

}
