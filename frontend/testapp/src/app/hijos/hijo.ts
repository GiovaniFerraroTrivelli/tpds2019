import { Sexo } from '../enums/sexo.enum';
import { EstadoCivil } from '../enums/estado-civil.enum';

export class Hijo {
	fechaNacimiento: Date;
	sexo: Sexo;
	estadoCivil: EstadoCivil;
	edad: number;
}
