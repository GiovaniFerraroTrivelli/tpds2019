import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Provincia } from './provincia';
import { Localidad } from './localidad';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class GeografiaService {

	private paisesUrl: string;
	private provinciasUrl: string;
	private localidadesUrl: string;

	constructor(private http: HttpClient) {
		this.paisesUrl = 'http://localhost:8080/paises';
		this.provinciasUrl = 'http://localhost:8080/provincias';
		this.localidadesUrl = 'http://localhost:8080/localidades';
	}

	public getProvinciasByPais(idPais : number): Observable<Provincia[]> {
		return this.http.get<Provincia[]>(this.provinciasUrl + "/" + idPais);
	}

	public getLocalidadesByProvincia(idProvincia : number): Observable<Localidad[]> {
		return this.http.get<Localidad[]>(this.localidadesUrl + "/" + idProvincia);
	}
}
