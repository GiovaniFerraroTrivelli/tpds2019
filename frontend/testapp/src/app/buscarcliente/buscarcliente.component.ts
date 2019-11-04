import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Cliente } from '../cliente/cliente';
import { TipoDNI } from '../enums/tipo-dni.enum';
import { BusquedaClienteService } from './busquedacliente.service';
import { DialogService } from '../dialog/dialog.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoadingService } from '../loading/loading.service';

@Component({
  selector: 'app-buscarcliente',
  templateUrl: './buscarcliente.component.html',
  styleUrls: ['./buscarcliente.component.scss']
})

export class BuscarclienteComponent implements OnInit {

	public TipoDNI = TipoDNI;
	private resultados : Cliente[];
	private selectedClient : Cliente;

	@Output()
	emitter = new EventEmitter<Cliente>();

	constructor(
		private busquedaClienteService: BusquedaClienteService,
		private dialogService: DialogService,
		private modalService: NgbModal,
		private loadingService: LoadingService
	) { }

	ngOnInit() {
	}

	getSelectedClient(){
		return this.selectedClient;
	}

	loadSelectedClient(resultado) {
		this.selectedClient = resultado;
	}

	emitCliente(){
		this.emitter.emit(this.selectedClient);
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

	onSubmit(f: NgForm, content) {
		this.loadingService.i();

		this.busquedaClienteService.postClienteBusqueda(f.value).subscribe(data => {
		    this.resultados = data;
		    if(this.resultados.length) {
		    	this.modalService.open(content, {
		    		centered: true,
		    		size: 'lg'
		    	});
		    } else {
		    	this.dialogService.alert(
		    		'Resultados de búsqueda',
		    		'Ningún cliente coincide con los criterios de búsqueda. Intente nuevamente.'
		    	);
		    }
		    
		    this.loadingService.d();
	    });
	}

	seleccionarCliente(modal) {
		this.emitCliente();
		this.loadSelectedClient(null);
		modal.close();
	}

	cancelarBusquedaCliente(modal) {
		this.loadSelectedClient(null);
		modal.close();
	}

}
