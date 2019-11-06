import { Component, OnInit, Output, Input } from '@angular/core';
import { TipoCobertura } from 'TipoCobertura';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TipocoberturaService } from './tipocobertura.service';
import { NgForm, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';

@Component({
	selector: 'app-tipocobertura',
	templateUrl: './tipocobertura.component.html',
	styleUrls: ['./tipocobertura.component.scss']
})
export class TipocoberturaComponent implements OnInit {
	@Input() coberturas: TipoCobertura[];
	coberturaSeleccionada: String;
	selCobForm: FormGroup;
	
	constructor(private modalService: NgbModal, private coberturaService: TipocoberturaService) { }
	
	ngOnInit() {
		this.selCobForm = new FormGroup({
			'fechaVigencia': new FormControl(null, Validators.required),
			'modalidadPago': new FormControl(null, Validators.required),
		});
	}
	
	openVerticallyCentered(content) {
		this.modalService.open(content, { centered: true });
	}

	seleccionarCobertura(cobertura) {
		this.coberturaSeleccionada = cobertura;
	}

	onSubmit(){
		console.log(this.coberturaSeleccionada)
	}
}
