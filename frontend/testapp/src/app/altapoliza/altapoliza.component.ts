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
import { Direccion } from '../cliente/direccion';
import { TipocoberturaComponent } from '../tipocobertura/tipocobertura.component';
import { TipoCobertura } from 'TipoCobertura';

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
	private cliente : Cliente;
	private cobertura: TipoCobertura;
	private toggleView: boolean;

	constructor(
		private titleService: Title,
		private geografiaService: GeografiaService,
		private modelosService: ModelosService,
	) { 
		this.toggleView = true;
	}

	ngOnInit() {
		this.cliente = new Cliente();
		this.cliente.direccion = new Direccion();
		this.cliente.direccion.localidad = new Localidad();

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
		this.toggleView = false;
	}

	processCliente(cliente) {
		this.cliente = cliente;
	}
	processReturn(volver) {
		this.toggleView = volver;
	}
}
