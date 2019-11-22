import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';

import { TipoCobertura } from '../tipocobertura/tipocobertura.enum';
import { MedidasSeguridad } from '../enums/medidas-seguridad.enum';

@Component({
  selector: 'app-actualizar-factores',
  templateUrl: './actualizar-factores.component.html',
  styleUrls: ['./actualizar-factores.component.scss']
})
export class ActualizarFactoresComponent implements OnInit {

	public title = "Actualizar factores de características";

	public TipoCobertura = TipoCobertura;
	public MedidasSeguridad = MedidasSeguridad;

	constructor(
		private titleService: Title,
	) { }

	ngOnInit() {
		this.titleService.setTitle(this.title);
	}

	onSubmit(f : NgForm) {
		console.log(f.value);
	}

}
