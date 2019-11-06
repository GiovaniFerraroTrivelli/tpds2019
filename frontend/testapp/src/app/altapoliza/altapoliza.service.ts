import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Poliza } from '../poliza/poliza';
import { RespuestaValidacion } from './respuesta-validacion';

@Injectable()
export class AltaPolizaService {

	private validarUrl: string;

	constructor(private http: HttpClient) {
		this.validarUrl = 'http://localhost:8080/verificarDatos';
	}

	public postValidarDatos(poliza : Poliza): Observable<RespuestaValidacion> {
		return this.http.post<RespuestaValidacion>(this.validarUrl, poliza);
	}
}
