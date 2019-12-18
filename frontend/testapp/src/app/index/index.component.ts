import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AuthenticationService } from '../authentication/authentication.service';
import { Rol } from '../enums/rol.enum';

@Component({
	selector: 'app-index',
	templateUrl: './index.component.html',
	styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {

	public Rol = Rol;

	constructor(
		private titleService: Title,
		private loginService: AuthenticationService
	) { }

	ngOnInit() {
		this.titleService.setTitle("Inicio - Trabajo Práctico - Diseño de Sistemas - 2019");
	}

	getUserName() {
		return this.loginService.getUserName();
	}

	getFullUserName() {
		return this.loginService.getFullUserName();
	}

	getRol() {
		return Rol[this.loginService.getRol()];
	}

	isUserLoggedIn() {
		return this.loginService.isUserLoggedIn();
	}
}
