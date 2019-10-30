import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { TipoDNI } from '../enums/tipo-dni.enum';

@Component({
  selector: 'app-buscarcliente',
  templateUrl: './buscarcliente.component.html',
  styleUrls: ['./buscarcliente.component.scss']
})

export class BuscarclienteComponent implements OnInit {

	constructor() { }

	public TipoDNI = TipoDNI;

	ngOnInit() {
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

	onSubmit(f: NgForm) {
		console.log(f.value);
	}
}
