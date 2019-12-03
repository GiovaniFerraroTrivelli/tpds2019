import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { Poliza } from '../poliza/poliza';
import { BusquedaPoliza } from './busquedapoliza';
import { PolizasRta } from './polizaRespuesta';
import { respuestaBuscarPoliza } from './respuestaBuscarPoliza';

@Injectable({
	providedIn: 'root'
})

export class BuscarpolizaService {
	private url: string;

	constructor(private http: HttpClient) { 
		this.url = window.location.protocol + '//' + window.location.hostname + ':8080/buscarPoliza'
	}

	public postBuscarPoliza(busquedaPoliza: BusquedaPoliza) {
		return this.http.post<respuestaBuscarPoliza>(this.url, busquedaPoliza, { withCredentials: true });
	}
}
