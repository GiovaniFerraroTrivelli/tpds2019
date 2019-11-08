import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Poliza } from '../poliza/poliza';
import { RespuestaValidacion } from './respuesta-validacion';

@Injectable()
export class AltaPolizaService {

	private validarUrl1: string;
	private validarUrl2: string;

	constructor(private http: HttpClient) {
		this.validarUrl1 = 'http://localhost:8080/altaPoliza/validarDatos';
		this.validarUrl2 = 'http://localhost:8080/altaPoliza/2';
	}

	public postValidarDatos1(poliza : Poliza): Observable<RespuestaValidacion> {
		return this.http.post<RespuestaValidacion>(this.validarUrl1, poliza);
	}

	public postValidarDatos2(poliza : Poliza): Observable<boolean> {
		return this.http.post<boolean>(this.validarUrl2, poliza);
	}
}
