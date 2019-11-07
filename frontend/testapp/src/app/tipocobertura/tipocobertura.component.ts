import { Component, OnInit, Output, Input, EventEmitter} from '@angular/core';
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
	@Output() tipoCoberturaEmitter = new EventEmitter<TipoCobertura>();
	coberturaSeleccionada: TipoCobertura;
	selCobForm: FormGroup;
	
	constructor(
		private modalService: NgbModal,
		private coberturaService: TipocoberturaService
	) { }
	
	ngOnInit() {
		this.selCobForm = new FormGroup({
			'fechaVigencia': new FormControl(null, Validators.required),
			'modalidadPago': new FormControl(null, Validators.required),
		});
	}

	get fechaVigencia() { return this.selCobForm.get('fechaVigencia'); }
	get modalidadPago() { return this.selCobForm.get('modalidadPago'); }

	openVerticallyCentered(content) {
		this.modalService.open(content, { centered: true });
	}

	seleccionarCobertura(cobertura, content) {
		this.coberturaSeleccionada = cobertura;

		// setear fecha de mañana
		let today = new Date();
		let tomorrow = new Date();
		tomorrow.setDate(today.getDate()+1);
		this.selCobForm.controls['fechaVigencia'].setValue(tomorrow.toISOString().substring(0,10));

		this.openVerticallyCentered(content);
	}

	cancelCobertura(f: NgForm, modal) {
		modal.close('cancel');
		f.reset();
	}

	onSubmitSelCob(f: NgForm, modal) {
		console.log(this.coberturaSeleccionada);

		console.log(f.value);

		modal.close('add');

		f.reset();
	}
	sendCobertura(){
		this.tipoCoberturaEmitter.emit(this.coberturaSeleccionada);
	}
}
