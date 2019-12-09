import { Injectable } from '@angular/core';
import { Recibo } from './recibo';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { RequestMontoTotal } from './requestMontoTotal';
import { RequestVuelto } from './requestVuelto';
import { ResponseMontoTotal } from './responseMontoTotal';
import { ResponseVuelto } from './responseVuelto';

@Injectable({
  providedIn: 'root'
})
export class RegistrarPagoService {
  private url1: string;
  private url2: string;
  private url3: string;

  constructor(private http: HttpClient) { 
    this.url1 = window.location.protocol + '//' + window.location.hostname + ':8080/recibo';
    this.url2 = window.location.protocol + '//' + window.location.hostname + ':8080/calcularImporteTotal';
    this.url3 = window.location.protocol + '//' + window.location.hostname + ':8080/registrarPago';
  }
  public postRecibo(recibo: Recibo){
    return this.http.post<Recibo>(this.url1, recibo, { withCredentials: true });
  }
  public postCalcularImporte(montoTotal: RequestMontoTotal){
    return this.http.post<ResponseMontoTotal>(this.url2, montoTotal, { withCredentials: true });
  }
  public postVuelto(requestVuelto: RequestVuelto){
    return this.http.post<ResponseVuelto>(this.url3, requestVuelto, { withCredentials: true });
  }
}
