import { ResumenPoliza } from './resumen-poliza';
import { Error } from '../error/error';

export class RespuestaResumen {
	errores: Error[];
	datosPoliza: ResumenPoliza;
}
