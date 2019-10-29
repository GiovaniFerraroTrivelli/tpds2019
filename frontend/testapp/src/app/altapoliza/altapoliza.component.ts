import { Component, OnInit, ViewChild } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { HijosComponent } from '../hijos/hijos.component';
import { NgForm } from '@angular/forms';
import { Hijo } from '../hijos/hijo';
import { GeografiaService } from '../geografia/geografia.service';
import { Provincia } from '../geografia/provincia';
import { Localidad } from '../geografia/localidad';

@Component({
	selector: 'app-altapoliza',
	templateUrl: './altapoliza.component.html',
	styleUrls: ['./altapoliza.component.scss']
})

export class AltapolizaComponent implements OnInit {

	@ViewChild(HijosComponent, {static:false}) childComp: HijosComponent;

	private provincias : Provincia[];
	private localidades : Localidad[];

	constructor(private titleService: Title, private geografiaService: GeografiaService) { }

	ngOnInit() {
		this.titleService.setTitle("Dar de alta póliza");
		this.getListaProvincias();
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

	ngAfterViewInit(): void {
	}

	onSubmit(f: NgForm) {
		console.log("aca abajo");
		let formJSON = f.value;
		formJSON.hijos = this.childComp.hijos;

		console.log(f.value);
	}
}
