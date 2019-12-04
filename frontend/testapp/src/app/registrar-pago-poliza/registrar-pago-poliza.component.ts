import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-registrar-pago-poliza',
  templateUrl: './registrar-pago-poliza.component.html',
  styleUrls: ['./registrar-pago-poliza.component.scss']
})
export class RegistrarPagoPolizaComponent implements OnInit {

	public title = "Registrar pago de póliza";
	public isCollapsed: boolean;

	constructor(
		private titleService: Title,
		private modalService: NgbModal,
	) { }

	ngOnInit() {
		this.isCollapsed = false;
		this.titleService.setTitle(this.title);
	}
}
