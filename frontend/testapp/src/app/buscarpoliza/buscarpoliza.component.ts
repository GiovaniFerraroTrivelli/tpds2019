import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-buscarpoliza',
  templateUrl: './buscarpoliza.component.html',
  styleUrls: ['./buscarpoliza.component.scss']
})

export class BuscarpolizaComponent implements OnInit {

	constructor() { }

	ngOnInit() {
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

	onSubmit(f: NgForm) {
		console.log(f.value);
	}
}
