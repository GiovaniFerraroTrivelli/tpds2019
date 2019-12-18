import { Hijo } from '../hijos/hijo';
import { ModalidadPago } from '../enums/modalidad-pago.enum';

export class Poliza {
	anio: number;
	chasis: string;
	hijos: Hijo[];
	nroCliente: number;
	localidad: number;
	marca: number;
	modelo: number;
	motor: string;
	nroSiniestros: number;
	patente: string;
	poseeAlarma: boolean;
	poseeRastreoVehicular: boolean;
	poseeTuercasAntirrobo: boolean;
	provincia: number;
	kmAnio: number;
	seGuardaEnGarage: boolean;

	idPoliza: number;
	idCobertura: number;
	fechaVigencia: string;
	modalidadPago: ModalidadPago;
}
