import { Cuota } from '../poliza/cuota';

export class Recibo{
    numeroPoliza: string;
    fecha: string;
    cuotas: Cuota[];
    operador: string;
    importeTotal: number;
}