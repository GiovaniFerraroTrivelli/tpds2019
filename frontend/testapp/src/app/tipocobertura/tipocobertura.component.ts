import { Component, OnInit, Output, Input, EventEmitter} from '@angular/core';
import { Router } from "@angular/router";
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgForm, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';

import { TipoCobertura } from 'TipoCobertura';
import { AltaPolizaService } from '../altapoliza/altapoliza.service';
import { LoadingService } from '../loading/loading.service';
import { DialogService } from '../dialog/dialog.service';
import { GlobalScriptsService } from '../global-scripts.service';

import { Poliza } from '../poliza/poliza';
import { RespuestaResumen } from '../poliza/respuesta-resumen';
import { ResumenPoliza } from '../poliza/resumen-poliza';
import { ModalidadPago } from '../enums/modalidad-pago.enum';
import { FechaVigenciaValidator } from './fechavigencia.validator';
import { TokenContainer } from './tokencontainer';

@Component({
	selector: 'app-tipocobertura',
	templateUrl: './tipocobertura.component.html',
	styleUrls: ['./tipocobertura.component.scss']
})
export class TipocoberturaComponent implements OnInit {
	@Input() coberturas: TipoCobertura[];
	@Input() polizaValues: Poliza;

	selCobForm: FormGroup;
	finVigencia: Date;
	fechaVigenciaDay: String;
	vencimientoCuota: String;
	coberturaSeleccionada: TipoCobertura;
	resumenPoliza: ResumenPoliza;
	private tokenContainer: TokenContainer;
	private token: string;

	public ModalidadPago = ModalidadPago;

	constructor(
		private modalService: NgbModal,
		private dialogService: DialogService,
		private altaPolizaService: AltaPolizaService,
		private loadingService: LoadingService,
		private global: GlobalScriptsService,
		private router: Router
	) { }
	
	ngOnInit() {
		this.selCobForm = new FormGroup({
			'idCobertura': new FormControl(null, Validators.required),
			'fechaVigencia': new FormControl(null, [ Validators.required, FechaVigenciaValidator ]),
			'modalidadPago': new FormControl(null, Validators.required),
		});

		this.polizaValues = new Poliza();
		this.resumenPoliza = new ResumenPoliza();
		this.tokenContainer = new TokenContainer();
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
		Object.assign(this.polizaValues, f.value);
		console.log(this.polizaValues);

		this.loadingService.i();

		this.altaPolizaService.postValidarDatos2(this.polizaValues).subscribe(
			data => {
			    console.log(data);

			    if(data.errores.length) {
			    	this.dialogService.alert(
			    		'Errores detectados',
			    		data.errores.map(e => e.mensaje).join(". ")
			    	);

					this.clearCobertura();
			    } else {
			    	this.resumenPoliza = data.datosPoliza;
			    	this.token = data.token;
			    }

			    this.loadingService.d();

			    modal.close('add');
			    f.reset();
			},
		    err => {
        		this.dialogService.alert(
		    		'Ha ocurrido un error',
		    		'No se pudo realizar lo solicitado: ' + err.error.error
		    	);
			    
			    this.loadingService.d();
      		}
		);
	}

	endStepCancelar() {
		this.dialogService.confirm(
			'Cancelar alta de póliza',
			'¿Está seguro que desea cancelar el alta de la póliza?', true, 'Sí', 'No')
			.then((confirmed) => {
				if(confirmed)
					this.router.navigate(['/']);
			}, () => {});
	}

	endStepCambiarCobertura() {
		this.clearCobertura();
	}

	endStepGenerar() {
		this.loadingService.i();

		this.tokenContainer.token = this.token;
		this.altaPolizaService.postValidarDatos3(this.tokenContainer).subscribe(
			data => {
			    this.loadingService.d();
			    console.log(data);

			    if(data === true) {
					this.dialogService.alert(
			    		'Póliza agregada',
			    		'La póliza fue agregada exitosamente al sistema.'
			    	);
			    } else {
					this.dialogService.alert(
			    		'Error de alta de póliza',
			    		'Hubo un error al cargar la póliza.'
			    	);
			    }
			},
			err => {
			    this.loadingService.d();
				
				if(err.status == 500) {
					this.dialogService.alert(
			    		'Error de alta de póliza',
			    		err.error.error
			    	);
				} else {
					this.dialogService.alert(
			    		'Error de alta de póliza',
			    		err.error.mensaje
			    	);
				}
			}
		);
	}

	clearCobertura() {
		this.polizaValues.idCobertura = undefined;
		this.polizaValues.fechaVigencia = undefined;
		this.polizaValues.modalidadPago = undefined;

		this.resumenPoliza = new ResumenPoliza();
	}

	hasResponsePost2() {
		return Object.keys(this.resumenPoliza).length;
	}
}
