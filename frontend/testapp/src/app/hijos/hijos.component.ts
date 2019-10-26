import { Component, OnInit, Input} from '@angular/core';
import { Hijo } from 'hijos';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Sexo } from '../enums/sexo.enum';
import { EstadoCivil } from '../enums/estado-civil.enum';

@Component({
	selector: 'app-hijos',
	templateUrl: './hijos.component.html',
	styleUrls: ['./hijos.component.scss']
})

export class HijosComponent implements OnInit {
	hijos: Hijo[];

	@Input() fechaNacimiento: Date;
	@Input() sexo: String;
	@Input() estadoCivil: String;
	edad: number;

	public Sexo = Sexo;
	public EstadoCivil = EstadoCivil;

	constructor(private modalService: NgbModal) {
		this.hijos = [];
	}
	
	ngOnInit() {}
	
	openVerticallyCentered(content) {
		this.modalService.open(content, { centered: true });
	}

	calcularEdad() {
		const today = new Date();
		const fechaNac = new Date(this.fechaNacimiento);
		let age = today.getFullYear() - fechaNac.getFullYear();
		const m = today.getMonth() - fechaNac.getMonth();

		if(m < 0 || (m === 0 && today.getDate() < fechaNac.getDate())) {
		  age--;
		}
		
		this.edad = age;
	}

	onSubmitHijoDialog(modal) {
		modal.close('add');
		this.calcularEdad();
		this.addHijo();
	}

	addHijo() {
		const hijo = new Hijo();
		hijo.edad = this.edad;
		hijo.estadoCivil = this.estadoCivil;
		hijo.sexo = this.sexo;
		this.hijos.push(hijo);
	}

	deleteHijo(index: number) {
		this.hijos.splice(index, 1);
	}
}
