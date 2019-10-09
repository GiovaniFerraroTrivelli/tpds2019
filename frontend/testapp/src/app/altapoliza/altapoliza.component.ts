import { Component, OnInit, ViewChild } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { HijosComponent } from '../hijos/hijos.component';
import { NgForm } from '@angular/forms';
import { Hijo } from 'hijos';

@Component({
	selector: 'app-altapoliza',
	templateUrl: './altapoliza.component.html',
	styleUrls: ['./altapoliza.component.scss']
})
export class AltapolizaComponent implements OnInit {

	@ViewChild(HijosComponent, {static:false}) childComp: HijosComponent;

	constructor(private titleService: Title) { }

	ngOnInit() {
		this.titleService.setTitle("Dar de alta póliza");
	}

	changeTest() {
		console.log(":v");
	}

	ngAfterViewInit(): void {
	}

	onSubmit(f: NgForm) {
		console.log("aca abajo");
		let formJSON = f.value;
		let hijosJSON = this.childComp.hijos;
		const mergeJSON = Object.assign({}, hijosJSON, formJSON);
		console.log(mergeJSON);
	}
}
