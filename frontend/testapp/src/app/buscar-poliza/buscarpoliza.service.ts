import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Poliza } from '../poliza/poliza'
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class BuscarpolizaService {
  private url: string;
  constructor(private http: HttpClient) { 
    this.url = 'localhost:8080/buscarPoliza'
  }

  public postBuscarPoliza(nroPoliza: number) {
		return this.http.post<number>(this.url, nroPoliza);
	}
}
