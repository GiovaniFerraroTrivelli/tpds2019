import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { TipoCobertura } from 'TipoCobertura';

@Injectable({
  providedIn: 'root'
})
export class TipocoberturaService {
  private tipoCoberturaUrl: string;
 
  constructor(private http: HttpClient) {
    this.tipoCoberturaUrl = 'http://localhost:8080/tipocobertura';
  }
 
  public findAll(): Observable<TipoCobertura[]> {
    return this.http.get<TipoCobertura[]>(this.tipoCoberturaUrl);
  }
 
  public save(tipoCobertura: TipoCobertura) {
    return this.http.post<TipoCobertura>(this.tipoCoberturaUrl, tipoCobertura);
  }
}
