import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserLogin } from '../user-login';
import { DialogService } from '../dialog/dialog.service';

@Injectable({
	providedIn: 'root'
})

export class AuthenticationService {

	private usersUrl: string;

	constructor(private http: HttpClient, private dialogService: DialogService) {
		this.usersUrl = 'http://localhost:8080/login';
	}

	authenticate(userLogin : UserLogin) {
		return this.http.post<UserLogin>(this.usersUrl, userLogin);
	}

	isUserLoggedIn() {
		let user = sessionStorage.getItem('username');
		return !(user === null);
	}

	logOut() {
		this.dialogService.confirm('Cerrar sesión', '¿Está seguro que desea cerrar su sesión?', true)
			.then((confirmed) => { if(confirmed) sessionStorage.removeItem('username') });
	}
}
