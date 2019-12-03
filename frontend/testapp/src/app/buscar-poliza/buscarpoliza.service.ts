import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { Poliza } from '../poliza/poliza';
import { BusquedaPoliza } from './busquedapoliza';

@Injectable({
	providedIn: 'root'
})

export class BuscarpolizaService {
	private url: string;

	constructor(private http: HttpClient) { 
		this.url = window.location.protocol + '//' + window.location.hostname + ':8080/buscarPoliza'
	}

	public postBuscarPoliza(busquedaPoliza: BusquedaPoliza) {
		return this.http.post<BusquedaPoliza>(this.url, busquedaPoliza, { withCredentials: true });
	}
}
