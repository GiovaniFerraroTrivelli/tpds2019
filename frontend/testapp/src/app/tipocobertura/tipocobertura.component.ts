import { Component, OnInit, Output, Input, EventEmitter} from '@angular/core';
import { TipoCobertura } from 'TipoCobertura';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgForm, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { Poliza } from '../poliza/poliza';
import { SeleccionCobertura } from './seleccion-cobertura';
import { DialogService } from '../dialog/dialog.service';
import { FechaVigenciaValidator } from './fechavigencia.validator';
import { Router } from "@angular/router";

@Component({
	selector: 'app-tipocobertura',
	templateUrl: './tipocobertura.component.html',
	styleUrls: ['./tipocobertura.component.scss']
})
export class TipocoberturaComponent implements OnInit {
	@Input() coberturas: TipoCobertura[];
	coberturaSeleccionada: TipoCobertura;
	selCobForm: FormGroup;
	@Input() polizaValues: Poliza;
	@Input() marcaSeleccionada: String;
	@Input() modeloSeleccionado: String;
	finVigencia: Date;

	constructor(
		private modalService: NgbModal,
		private dialogService: DialogService,
		private router: Router
	) { }
	
	ngOnInit() {
		this.selCobForm = new FormGroup({
			'idCobertura': new FormControl(null, Validators.required),
			'fechaVigencia': new FormControl(null, [ Validators.required, FechaVigenciaValidator ]),
			'modalidadPago': new FormControl(null, Validators.required),
		});

		this.polizaValues = new Poliza();
		this.polizaValues.seleccionCobertura = new SeleccionCobertura();
	}

	get idCobertura() { return this.selCobForm.get('idCobertura'); }
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

		this.selCobForm.controls['idCobertura'].setValue(cobertura.idCobertura);

		this.openVerticallyCentered(content);
	}

	cancelCobertura(f: NgForm, modal) {
		modal.close('cancel');
		f.reset();
	}

	onSubmitSelCob(f: NgForm, modal) {
		modal.close('add');

		this.polizaValues.seleccionCobertura = f.value;
		
		this.finVigencia = new Date(this.selCobForm.get('fechaVigencia').value);
		this.finVigencia.setMonth(this.finVigencia.getMonth() + 6);

		console.log(this.polizaValues);

		f.reset();
	}

	endStepCancelar() {
		this.dialogService.confirm(
			'Cancelar alta de póliza',
			'¿Está seguro que desea cancelar el alta de la póliza?', true, 'Sí', 'No')
			.then((confirmed) => {
				if(confirmed)
					this.router.navigate(['/']);
			});
	}

	endStepCambiarCobertura() {
		this.polizaValues.seleccionCobertura = null;
	}
}
