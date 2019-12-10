import { Documento } from '../cliente/documento';
import { UltimoPago } from '../poliza/ultimoPago';

export class PolizasRta{
    idPoliza: number;
    idCliente: string;
    apellidoCliente: string;
    nombreCliente: string;
    documento: Documento;
    numeroPoliza: string;
    numeroCliente: string;
	ultimoPago: UltimoPago;
}