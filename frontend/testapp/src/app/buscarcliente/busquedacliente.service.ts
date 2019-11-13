import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cliente } from '../cliente/cliente';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class BusquedaClienteService {

	private buscarUrl: string;

	constructor(private http: HttpClient) {
		this.buscarUrl = 'http://localhost:8080/buscarCliente';
	}

	public postClienteBusqueda(cliente : Cliente): Observable<Cliente[]> {
		return this.http.post<Cliente[]>(this.buscarUrl, cliente);
	}
}