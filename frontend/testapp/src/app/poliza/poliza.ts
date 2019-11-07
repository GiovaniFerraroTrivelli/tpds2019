import { Hijo } from '../hijos/hijo';
import { SeleccionCobertura } from '../tipocobertura/seleccion-cobertura';

export class Poliza {
	anio: number;
	chasis: string;
	hijos: Hijo[];
	idcliente: number;
	localidad: number;
	marca: number;
	modelo: number;
	motor: string;
	nroSiniestros: number;
	patente: string;
	poseeAlarma: boolean;
	poseeRastreoVehicular: boolean;
	poseeTuercasAntirrobo: boolean;
	provincia: number;
	seGuardaEnGarage: boolean;
	seleccionCobertura: SeleccionCobertura;

	idPoliza: number;
}
