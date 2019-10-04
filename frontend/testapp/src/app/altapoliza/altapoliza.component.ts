import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { HijosComponent } from '../hijos/hijos.component'

@Component({
	selector: 'app-altapoliza',
	templateUrl: './altapoliza.component.html',
	styleUrls: ['./altapoliza.component.scss']
})
export class AltapolizaComponent implements OnInit {

	constructor(private titleService: Title) { }

	ngOnInit() {
		this.titleService.setTitle("Dar de alta póliza");
	}

	changeTest() {
		console.log(":v");
	}
}
