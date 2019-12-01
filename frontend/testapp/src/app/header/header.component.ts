import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication/authentication.service';
import { Router } from "@angular/router";
import { Rol } from '../enums/rol.enum';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.scss']
})

export class HeaderComponent implements OnInit {

	public isMenuCollapsed = true;
	
	constructor(
		private loginService: AuthenticationService,
		private router: Router
	) {}

	ngOnInit() {
	}

	hasPermission(rol : Rol) {
		return this.loginService.hasPermission(rol);
	}

	getUserName() {
		return this.loginService.getUserName();
	}

	isUserLoggedIn() {
		return this.loginService.isUserLoggedIn();
	}

	userLogOut() {
		return this.loginService.logOut();
	}

	isCurrentRouter(router : string) {
		return this.router.isActive(router, true);
	}
}
