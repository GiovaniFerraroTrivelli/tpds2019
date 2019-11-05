import { Sexo } from '../enums/sexo.enum';
import { Direccion } from './direccion';
import { Documento } from './documento';
import { CondicionIva } from '../enums/condicion-iva.enum';

export class Cliente {
	idCliente: number;
	nombre: string;
	apellido: string;
	documento: Documento;
	cuil: number;
	sexo: Sexo;
	fechaNacimiento: string;
	profesion: string;
	estadoCivil: string;
	email: string;
	condicionIva: CondicionIva;
	direccion: Direccion;
}
