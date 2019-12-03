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
	private url1: string;
	private url2: string;

	constructor(private http: HttpClient) { 
		this.url1 = window.location.protocol + '//' + window.location.hostname + ':8080/buscarPoliza';
		this.url2 = window.location.protocol + '//' + window.location.hostname + ':8080/poliza';
	}

	public postBuscarPoliza(busquedaPoliza: BusquedaPoliza) {
		return this.http.post<respuestaBuscarPoliza>(this.url1, busquedaPoliza, { withCredentials: true });
	}
	public getPolizaSeleccionada(idPoliza: number){
		console.log(this.url2+idPoliza);
		return this.http.get(this.url2 + "/" + idPoliza, { withCredentials: true });
	}
}
