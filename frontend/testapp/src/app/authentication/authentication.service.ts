import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { DialogService } from '../dialog/dialog.service';
import { UserLogin } from '../login/user-login';
import { Rol } from '../enums/rol.enum';

@Injectable({
	providedIn: 'root'
})

export class AuthenticationService {

	private usersUrl: string;
	private logoutUrl: string;

	constructor(private http: HttpClient, private dialogService: DialogService) {
		this.usersUrl = window.location.protocol + '//' + window.location.hostname + ':8080/login';
		this.logoutUrl = window.location.protocol + '//' + window.location.hostname + ':8080/logout';
	}

	authenticate(userLogin : UserLogin): Observable<UserLogin> {
		return this.http.post<UserLogin>(this.usersUrl, userLogin, { withCredentials: true });
	}

	isUserLoggedIn() {
		let user = sessionStorage.getItem('nombreUsuario');
		return !(user === null);
	}

	getUserName() {
		return sessionStorage.getItem('nombreUsuario');
	}

	getFullUserName() {
		return sessionStorage.getItem('nombre') + " " + sessionStorage.getItem('apellido');
	}

	getRol() {
		return sessionStorage.getItem('rol');
	}

	hasPermission(required : Rol) {
		let rol = sessionStorage.getItem('rol');
		return (required === rol);
	}

	logOut() {
		this.dialogService.confirm('Cerrar sesión', '¿Está seguro que desea cerrar su sesión?', true)
			.then((confirmed) => {
				if(confirmed) {
					sessionStorage.clear();
					this.http.post(this.logoutUrl, null, { withCredentials: true }).subscribe();
				}
			});
	}
}
