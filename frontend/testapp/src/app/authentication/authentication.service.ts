import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router, CanActivate, ActivatedRouteSnapshot } from "@angular/router";

import { DialogService } from '../dialog/dialog.service';
import { UserLogin } from '../login/user-login';
import { Rol } from '../enums/rol.enum';

@Injectable({
	providedIn: 'root'
})

export class AuthenticationService implements CanActivate {

	private usersUrl: string;
	private checkLoginUrl: string;
	private logoutUrl: string;

	routesRol = {
		"alta-poliza": Rol.ProductorDeSeguros,
		"consultar-poliza": Rol.ProductorDeSeguros,
		"buscar-cliente": Rol.ProductorDeSeguros,
		"alta-cliente": Rol.ProductorDeSeguros,
		"buscar-poliza": Rol.Cobrador,
		"consultar-clientes": Rol.ProductorDeSeguros,
		"actualizar-factores": Rol.ProductorDeSeguros,
		"generar-informe-mensual": Rol.Gerente,
		"registrar-pago-poliza": Rol.Cobrador
	};

	constructor(
		private http: HttpClient,
		private dialogService: DialogService,
		private router: Router
	) {
		this.usersUrl = window.location.protocol + '//' + window.location.hostname + ':8080/login';
		this.checkLoginUrl = window.location.protocol + '//' + window.location.hostname + ':8080/checkLogin';
		this.logoutUrl = window.location.protocol + '//' + window.location.hostname + ':8080/logout';
	}

	canActivate(route : ActivatedRouteSnapshot): boolean {
		if (!this.isUserLoggedIn()) {
			this.router.navigate(['error']);
			return false;
		}

		console.log("Chequeando permisos -> requerido: %s, tiene: %s", this.routesRol[route.url.toString()], Rol[this.getRol()]);

		if(this.routesRol[route.url.toString()] !== Rol[this.getRol()]) {
			console.warn("Sin permisos para entrar a esta ruta");

			this.router.navigate(['error']);
			return false;
		}

		this.checkLoginValid();
		return true;
	}

	checkLoginValid() {
		this.http.get<HttpResponse<UserLogin>>(this.checkLoginUrl, { withCredentials: true, observe: 'response' }).subscribe(
			(response: HttpResponse<any>) => {
				let serverTime = new Date(response.headers.get('Date')).getTime() / 1000;
				let userTime = new Date().getTime() / 1000;
				let diffTime = Math.abs(serverTime - userTime);

				if(diffTime >= 60) {
					this.router.navigate(['error']);
				}

				if(response.body.nombreUsuario != this.getUserName()) {
					sessionStorage.clear();

					this.dialogService.confirm(
						'Sesión cerrada',
						'Ha iniciado sesión con otra cuenta. Inicie sesión nuevamente.',
						true, 'Iniciar sesión', 'Ir al inicio'
					).then(confirmed => {
							if(confirmed) {
								this.router.navigate(['login']);
							} else {
								this.router.navigate(['']);
							}
						}
					);
				}
			},
			error => {
				sessionStorage.clear();

				this.dialogService.confirm(
					'Sesión cerrada',
					'Su sesión fue cerrada por el servidor. Inicie sesión nuevamente.',
					true, 'Iniciar sesión', 'Ir al inicio'
				).then(confirmed => {
						if(confirmed) {
							this.router.navigate(['login']);
						} else {
							this.router.navigate(['']);
						}
					}
				);
			}
		);
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
					this.router.navigate(['/']);
				}
			});
	}
}
