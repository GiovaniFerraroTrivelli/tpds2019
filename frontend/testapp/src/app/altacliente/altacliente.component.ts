import { Title } from '@angular/platform-browser';
import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { CondicionIva } from '../enums/condicion-iva.enum';
import { Sexo } from '../enums/sexo.enum';
import { EstadoCivil } from '../enums/estado-civil.enum';
import { TipoDNI } from '../enums/tipo-dni.enum';

@Component({
  selector: 'app-altacliente',
  templateUrl: './altacliente.component.html',
  styleUrls: ['./altacliente.component.scss']
})
export class AltaclienteComponent implements OnInit {

	constructor(private titleService: Title) { }

	public CondicionIva = CondicionIva;
	public Sexo = Sexo;
	public EstadoCivil = EstadoCivil;
	public TipoDNI = TipoDNI;

	ngOnInit() {
		this.titleService.setTitle("Dar de alta cliente");
	}

	onSubmit(f: NgForm) {
		console.log("aca abajo");
		let formJSON = f.value;
		console.log(f.value);
	}
}
