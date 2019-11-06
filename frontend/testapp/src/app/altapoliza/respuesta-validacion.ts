import { Error } from '../error/error';
import { TipoCobertura } from '../tipocobertura/tipo-cobertura';

export interface RespuestaValidacion {
	errores: Error[];
	coberturasDisponibles: TipoCobertura[];
}
