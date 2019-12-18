import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
	selector: 'app-consultarpoliza',
	templateUrl: './consultarpoliza.component.html',
	styleUrls: ['./consultarpoliza.component.scss']
})
export class ConsultarpolizaComponent implements OnInit {

	constructor(private titleService: Title) { }

	ngOnInit() {
		this.titleService.setTitle("Consultar póliza");
	}

}
