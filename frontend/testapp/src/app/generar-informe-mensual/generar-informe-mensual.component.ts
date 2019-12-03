import { Component, OnInit, ViewChild } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NgForm, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { Router } from "@angular/router";

import { LoadingService } from '../loading/loading.service';
import { AuthenticationService } from '../authentication/authentication.service';

import html2canvas from 'html2canvas';
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

@Component({
	selector: 'app-generar-informe-mensual',
	templateUrl: './generar-informe-mensual.component.html',
	styleUrls: ['./generar-informe-mensual.component.scss']
})
export class GenerarInformeMensualComponent implements OnInit {

	public title = "Generar informe de resultado mensual";
	public years: number[];
	public months: Object;
	public informeGenerado: boolean;

	objectKeys = Object.keys;

	generarInformeForm: FormGroup;

	constructor(
		private titleService: Title,
		private loadingService: LoadingService,
		private loginService: AuthenticationService,
		private router: Router
	) { }

	ngOnInit() {
		this.titleService.setTitle(this.title);

		this.generarInformeForm = new FormGroup({
			'mes': new FormControl(null),
			'anio': new FormControl(null)
		});

		// mostrar los últimos 5 años nomás
		this.years = [];
		for(let i = 0, j = new Date().getFullYear(); i <= 5; i++, j--)
			this.years.push(j);

		this.months = {
			1: "Enero",
			2: "Febrero",
			3: "Marzo",
			4: "Abril",
			5: "Mayo",
			6: "Junio",
			7: "Julio",
			8: "Agosto",
			9: "Septiembre",
			10: "Octubre",
			11: "Noviembre",
			12: "Diciembre"
		};

		this.informeGenerado = false;
	}

	disableSort() {
		return 0;
	}

	cancelar() {
		this.router.navigate(['/']);
	}

	getFullUserName() {
		return this.loginService.getFullUserName();
	}

	getCurrentDay() {
		let today = new Date();
		let dd = String(today.getDate()).padStart(2, '0');
		let mm = String(today.getMonth() + 1).padStart(2, '0');
		let yyyy = today.getFullYear();

		return dd + '/' + mm + '/' + yyyy;
	}

	onSubmit(f : NgForm) {
		console.log(f.value);

		if(f.value.mes == null) {
			console.log("EL MES ES NULOOOOOOOOOOOOOOOOOOO");
		}

		if(f.value.anio == null) {
			console.log("EL ANIO ES NULOOOOOOOOOOOOOOOOOOO");
		}

		this.informeGenerado = true;
	}

	imprimir() {
//		window.print();
		
		//const documentDefinition = { content: '<span style="color:red">PERDI THE GAME</span>!' };
		//pdfMake.createPdf(documentDefinition).print();

		html2canvas(document.getElementById('exportar')).then(function(canvas) {
			var data = canvas.toDataURL();
			var docDefinition = {
			    content: [{
			        image: data,
			        width: 500
			    }]
			};

			//window.open(data);
			pdfMake.createPdf(docDefinition).print();
		});
	}
}
