import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Cliente } from '../cliente/cliente';
//import { TipoDNI } from '../enums/tipo-dni.enum';
import { BusquedaClienteService } from './busquedacliente.service';
import { DialogService } from '../dialog/dialog.service';

@Component({
  selector: 'app-buscarcliente',
  templateUrl: './buscarcliente.component.html',
  styleUrls: ['./buscarcliente.component.scss']
})

export class BuscarclienteComponent implements OnInit {

	//private TipoDNI : TipoDNI;
	private resultados : Cliente[];

	constructor(
		private busquedaClienteService: BusquedaClienteService,
		private dialogService: DialogService
	) { }

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
		this.busquedaClienteService.postClienteBusqueda(f.value).subscribe(data => {
		    this.resultados = data;

		    if(this.resultados.length) {
		    	// aca
		    } else {
		    	this.dialogService.alert(
		    		'Resultados de búsqueda',
		    		'Ningún cliente coincide con los criterios de búsqueda. Intente nuevamente.'
		    	);
		    }
	    });
	}
}
