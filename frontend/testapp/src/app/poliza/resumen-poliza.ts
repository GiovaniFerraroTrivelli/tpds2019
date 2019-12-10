import { Cuota } from './cuota';
import { ModalidadPago } from '../enums/modalidad-pago.enum';

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
	ultimoDiaPago: string;
	montoTotal: number;
	formaPago: ModalidadPago;
	cuotas: Cuota[];
    token: string;
}
