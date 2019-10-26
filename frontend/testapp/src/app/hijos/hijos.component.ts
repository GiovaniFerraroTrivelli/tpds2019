import { Component, OnInit, Input} from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Hijo } from './hijo';
import { Sexo } from '../enums/sexo.enum';
import { EstadoCivil } from '../enums/estado-civil.enum';
import { NgForm, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
	selector: 'app-hijos',
	templateUrl: './hijos.component.html',
	styleUrls: ['./hijos.component.scss']
})

export class HijosComponent implements OnInit {
	public Sexo = Sexo;
	public EstadoCivil = EstadoCivil;

	hijos: Hijo[];
	hijoForm: FormGroup;

	_fechaNacimiento: Date;
	_sexo: Sexo;
	_estadoCivil: EstadoCivil;
	_edad: number;

	constructor(private modalService: NgbModal) {
		this.hijos = [];
	}
	
	ngOnInit() {
		this.hijoForm = new FormGroup({
			'fechaNacimiento': new FormControl(null, Validators.required),
			'sexo': new FormControl(null, Validators.required),
			'estadoCivil': new FormControl(null, Validators.required)
		});
	}
	
	get fechaNacimiento() { return this.hijoForm.get('fechaNacimiento'); }
	get sexo() { return this.hijoForm.get('sexo'); }
	get estadoCivil() { return this.hijoForm.get('estadoCivil'); }
	get edad() { return this.hijoForm.get('edad'); }

	openVerticallyCentered(content) {
		this.modalService.open(content, { centered: true });
	}

	calcularEdad() {
		const today = new Date();
		const fechaNac = new Date(this.hijoForm.get('fechaNacimiento').value);
		let age = today.getFullYear() - fechaNac.getFullYear();
		const m = today.getMonth() - fechaNac.getMonth();

		if(m < 0 || (m === 0 && today.getDate() < fechaNac.getDate())) {
		  age--;
		}
		
		this._edad = age;
	}

	onSubmitHijoDialog(f: NgForm, modal) {
		if(!f.valid || this._edad < 18 || this._edad > 30) {
			return false;
		}

		console.log(f.value);

		const hijo = new Hijo();
		hijo.fechaNacimiento = this.fechaNacimiento.value;
		hijo.sexo = this.sexo.value;
		hijo.estadoCivil = this.estadoCivil.value;
		hijo.edad = this._edad;

		modal.close('add');

		this.hijos.push(hijo);
		f.reset();
	}

	deleteHijo(index: number) {
		this.hijos.splice(index, 1);
	}

	edadIsValid() {
		return this._edad > 18 && this._edad < 30;
	}

	cancelHijo(f: NgForm, modal) {
		modal.close('cancel');
		f.reset();
	}
}
