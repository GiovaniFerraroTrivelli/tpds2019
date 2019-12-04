import { Injectable } from '@angular/core';
import { Recibo } from './recibo';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class RegistrarPagoService {
  private url: string;
  constructor(private http: HttpClient) { 
    this.url = window.location.protocol + '//' + window.location.hostname + ':8080/recibo';
  }
  public postRecibo(recibo: Recibo){
    return this.http.post<Recibo>(this.url, recibo, { withCredentials: true });
  }
}
