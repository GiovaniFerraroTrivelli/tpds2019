import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cliente } from '../cliente/cliente';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ConsultarclientesService {

	private buscarUrl: string;

	constructor(private http: HttpClient) {
		this.buscarUrl = window.location.protocol + '//' + window.location.hostname + ':8080/consultarCliente';
	}

	public postClienteBusqueda(cliente : Cliente): Observable<Cliente[]> {
		return this.http.post<Cliente[]>(this.buscarUrl, cliente);
	}
}
