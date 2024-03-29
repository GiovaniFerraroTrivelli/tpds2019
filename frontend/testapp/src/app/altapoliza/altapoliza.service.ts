import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Poliza } from '../poliza/poliza';
import { ResumenPoliza } from '../poliza/resumen-poliza';
import { RespuestaResumen } from '../poliza/respuesta-resumen';
import { RespuestaValidacion } from './respuesta-validacion';
import { TokenContainer } from '../tipocobertura/tokencontainer';

@Injectable()
export class AltaPolizaService {

	private validarUrl1: string;
	private validarUrl2: string;
	private validarUrl3: string;

	constructor(private http: HttpClient) {
		this.validarUrl1 = window.location.protocol + '//' + window.location.hostname + ':8080/altaPoliza/1';
		this.validarUrl2 = window.location.protocol + '//' + window.location.hostname + ':8080/altaPoliza/2';
		this.validarUrl3 = window.location.protocol + '//' + window.location.hostname + ':8080/altaPoliza/confirmar';
	}

	public postValidarDatos1(poliza : Poliza): Observable<RespuestaValidacion> {
		return this.http.post<RespuestaValidacion>(this.validarUrl1, poliza, { withCredentials: true });
	}

	public postValidarDatos2(poliza : Poliza): Observable<RespuestaResumen> {
		return this.http.post<RespuestaResumen>(this.validarUrl2, poliza, { withCredentials: true });
	}

	public postValidarDatos3(tokenContainer : TokenContainer): Observable<boolean> {
		return this.http.post<boolean>(this.validarUrl3, tokenContainer, { withCredentials: true });
	}
}
