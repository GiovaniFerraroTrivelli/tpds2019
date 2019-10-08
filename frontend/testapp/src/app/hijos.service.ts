import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Hijo } from 'hijos'

@Injectable()
export class HijosService {

  private hijosUrl: string;
 
  constructor(private http: HttpClient) {
    this.hijosUrl = 'http://localhost:8080/hijos';
  }

  public save(hijo: Hijo) {
    return this.http.post<Hijo>(this.hijosUrl, hijo);
  }

}
