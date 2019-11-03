import { Sexo } from '../enums/sexo.enum';
import { Direccion } from './direccion';
import { CondicionIva } from '../enums/condicion-iva.enum';

export class Cliente {
	idCliente: number;
	nombre: string;
	apellido: string;
	tipoDocumento: string;
	nroDocumento: string;
	cuil: number;
	sexo: Sexo;
	fechaNacimiento: string;
	profesion: string;
	estadoCivil: string;
	email: string;
	condicionIva: CondicionIva;
	direccion: Direccion;
}
