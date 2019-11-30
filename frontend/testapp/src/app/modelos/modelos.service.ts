import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Marca } from './marca';
import { Modelo } from './modelo';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ModelosService {

	private marcasUrl: string;
	private modelosUrl: string;
	private aniosUrl: string;

	constructor(private http: HttpClient) {
		this.marcasUrl = window.location.protocol + '//' + window.location.hostname + ':8080/marcas';
		this.modelosUrl = window.location.protocol + '//' + window.location.hostname + ':8080/modelos';
		this.aniosUrl = window.location.protocol + '//' + window.location.hostname + ':8080/anios';
	}

	public getMarcas(): Observable<Marca[]> {
		return this.http.get<Marca[]>(this.marcasUrl, { withCredentials: true });
	}

	public getModelosByMarca(idMarca : number): Observable<Modelo[]> {
		return this.http.get<Modelo[]>(this.modelosUrl + "/" + idMarca, { withCredentials: true });
	}

	public getAniosByModelo(idModelo : number): Observable<number[]> {
		return this.http.get<number[]>(this.aniosUrl + "/" + idModelo, { withCredentials: true });
	}
}
