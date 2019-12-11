import { UltimoPago } from './ultimoPago';

export class Cuota {
	idCuota: number;
	importe: number;
	estadoCuota: string;
	fechaVencimiento: string;
	importeFinal: number;
	ultimoPago: UltimoPago;
}
