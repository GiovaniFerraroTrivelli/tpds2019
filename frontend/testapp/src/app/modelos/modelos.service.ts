import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Marca } from './marca';
import { Modelo } from './modelo';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ModelosService {

	private marcasUrl: string;
	private modelosUrl: string;

	constructor(private http: HttpClient) {
		this.marcasUrl = 'http://localhost:8080/marcas';
		this.modelosUrl = 'http://localhost:8080/modelos';
	}

	public getMarcas(): Observable<Marca[]> {
		return this.http.get<Marca[]>(this.marcasUrl);
	}

	public getModelosByMarca(idMarca : number): Observable<Modelo[]> {
		return this.http.get<Modelo[]>(this.modelosUrl + "/" + idMarca);
	}
}
