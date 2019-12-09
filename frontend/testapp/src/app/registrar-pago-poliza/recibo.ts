import { Cuota } from '../poliza/cuota';

export class Recibo{
    idPoliza: number;
    fecha: string;
    cuotas: Cuota[];
    operador: string;
    importeTotal: number;
}