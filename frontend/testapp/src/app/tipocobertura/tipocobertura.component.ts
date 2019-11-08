import { Component, OnInit, Output, Input, EventEmitter} from '@angular/core';
import { TipoCobertura } from 'TipoCobertura';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgForm, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { Poliza } from '../poliza/poliza';
import { ModalidadPago } from '../enums/modalidad-pago.enum';
import { DialogService } from '../dialog/dialog.service';
import { FechaVigenciaValidator } from './fechavigencia.validator';
import { Router } from "@angular/router";
import { AltaPolizaService } from '../altapoliza/altapoliza.service';
import { LoadingService } from '../loading/loading.service';

@Component({
	selector: 'app-tipocobertura',
	templateUrl: './tipocobertura.component.html',
	styleUrls: ['./tipocobertura.component.scss']
})
export class TipocoberturaComponent implements OnInit {
	@Input() coberturas: TipoCobertura[];
	@Input() polizaValues: Poliza;
	@Input() marcaSeleccionada: String;
	@Input() modeloSeleccionado: String;

	selCobForm: FormGroup;
	finVigencia: Date;
	formaPago: String;
	fechaVigenciaDay: String;
	vencimientoCuota: String;
	coberturaSeleccionada: TipoCobertura;

	public ModalidadPago = ModalidadPago;

	private cuotas = [
		{ importe: 167, mesCorrespondiente: 10, anio: 2019, cuotaPaga: false },
		{ importe: 167, mesCorrespondiente: 11, anio: 2019, cuotaPaga: false },
		{ importe: 167, mesCorrespondiente: 12, anio: 2019, cuotaPaga: false },
	];

	cantidadCuotas = this.cuotas.length;

	constructor(
		private modalService: NgbModal,
		private dialogService: DialogService,
		private altaPolizaService: AltaPolizaService,
		private loadingService: LoadingService,
		private router: Router
	) { }
	
	ngOnInit() {
		this.selCobForm = new FormGroup({
			'idCobertura': new FormControl(null, Validators.required),
			'fechaVigencia': new FormControl(null, [ Validators.required, FechaVigenciaValidator ]),
			'modalidadPago': new FormControl(null, Validators.required),
		});

		this.polizaValues = new Poliza();
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

		Object.assign(this.polizaValues, f.value);
		console.log(this.polizaValues);

		this.finVigencia = new Date(this.selCobForm.get('fechaVigencia').value);
		this.finVigencia.setMonth(this.finVigencia.getMonth() + 6);
		console.log(this.polizaValues.fechaVigencia)
		this.fechaVigenciaDay = this.polizaValues.fechaVigencia[8]+this.polizaValues.fechaVigencia[9];
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
		this.polizaValues.idCobertura = undefined;
		this.polizaValues.fechaVigencia = undefined;
		this.polizaValues.modalidadPago = undefined;
	}

	setFormaPago(formaPago){
		this.formaPago = formaPago;
		console.log(this.formaPago)
	}
	vencimiento(cuota){
		if(cuota.mesCorrespondiente == 12) return `01/${cuota.anio+1}`;
		else return cuota.mesCorrespondiente + '/' + cuota.anio;
	}

	endStepGenerar() {
		this.loadingService.i();

		this.altaPolizaService.postValidarDatos2(this.polizaValues).subscribe(data => {
		    this.loadingService.d();
		    console.log(data);

		    if(data === true) {
				this.dialogService.alert(
		    		'Póliza agregada',
		    		'La póliza fue agregada exitosamente al sistema.'
		    	);
		    }

		    /*if(data.errores.length) {
		    	this.dialogService.alert(
		    		'Errores detectados',
		    		data.errores.map(e => e.mensaje).join(". ")
		    	);
		    } else {
		    	this.polizaValues = f.value;
		    	this.coberturasDisponibles = data.coberturasDisponibles;
				this.nextStep = true;
				this.setMarca();
				this.setModelo();
		    }*/
		});
	}
}
