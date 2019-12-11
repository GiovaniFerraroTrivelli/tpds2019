import { Cuota } from './cuota';
import { ModalidadPago } from '../enums/modalidad-pago.enum';
import { UltimoPago } from './ultimoPago';

export class ResumenPoliza {
	nombreTitular: string;
	apellidoTitular: string;
	marca: string;
	modelo: string;
	motor: string;
	chasis: string;
	patente: string;
	inicioVigencia: string;
	finVigencia: string;
	sumaAsegurada: number;
	premio: number;
	descuentos: number;
	montoTotalAlta: number;
	formaPago: ModalidadPago;
	cuotas: Cuota[];
    token: string;
}
