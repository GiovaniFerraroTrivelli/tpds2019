import { Component, OnInit, ViewChild } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { HijosComponent } from '../hijos/hijos.component';
import { NgForm } from '@angular/forms';
import { Hijo } from '../hijos/hijo';
import { GeografiaService } from '../geografia/geografia.service';
import { Provincia } from '../geografia/provincia';
import { Localidad } from '../geografia/localidad';
import { ModelosService } from '../modelos/modelos.service';
import { Marca } from '../modelos/marca';
import { Modelo } from '../modelos/modelo';
import { Cliente } from '../cliente/cliente';

@Component({
	selector: 'app-altapoliza',
	templateUrl: './altapoliza.component.html',
	styleUrls: ['./altapoliza.component.scss']
})

export class AltapolizaComponent implements OnInit {

	@ViewChild(HijosComponent, {static:false}) childComp: HijosComponent;

	private provincias : Provincia[];
	private localidades : Localidad[];
	private marcas : Marca[];
	private modelos : Modelo[];
	private clienteSeleccionado : Cliente;
	private idClientePlaceholder = '';
	private nombrePlaceholder = '';
	private apellidoPlaceholder = '';
	private tipoDocPlaceholder = '';
	private documentoPlaceholder = '';
	private direccionPlaceholder = '';
	

	constructor(
		private titleService: Title,
		private geografiaService: GeografiaService,
		private modelosService: ModelosService,
	) { }

	ngOnInit() {
		this.titleService.setTitle("Dar de alta póliza");
		this.getListaProvincias();
		this.getListaMarcas();
	}

	getListaProvincias(): void {
		this.geografiaService.getProvinciasByPais(1).subscribe(data => {
		    this.provincias = data;
	    });
	}

	changeProvincia(idProvincia) {
		this.geografiaService.getLocalidadesByProvincia(idProvincia).subscribe(data => {
		    this.localidades = data;
	    });
	}

	getListaMarcas(): void {
		this.modelosService.getMarcas().subscribe(data => {
		    this.marcas = data;
	    });
	}

	changeMarca(idMarca) : void {
		this.modelosService.getModelosByMarca(idMarca).subscribe(data => {
		    this.modelos = data;
	    });
	}

	ngAfterViewInit(): void {
	}

	onSubmit(f: NgForm) {
		console.log("aca abajo");
		let formJSON = f.value;
		formJSON.hijos = this.childComp.hijos;

		console.log(f.value);
	}
	processCliente(cliente){
		this.clienteSeleccionado = cliente;
		this.idClientePlaceholder = this.clienteSeleccionado.idCliente.toString();
		this.nombrePlaceholder = this.clienteSeleccionado.nombre;
		this.apellidoPlaceholder = this.clienteSeleccionado.apellido;
		this.tipoDocPlaceholder = this.clienteSeleccionado.tipoDocumento;
		this.documentoPlaceholder = this.clienteSeleccionado.nroDocumento.toString();
		this.direccionPlaceholder = this.clienteSeleccionado.direccion.calle + ' ' + this.clienteSeleccionado.direccion.numero.toString();
		console.log(this.clienteSeleccionado)
	}
}
