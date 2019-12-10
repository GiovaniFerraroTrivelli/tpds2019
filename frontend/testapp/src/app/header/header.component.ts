import { Component, OnInit, Inject } from '@angular/core';
import { AuthenticationService } from '../authentication/authentication.service';
import { Router } from "@angular/router";
import { Rol } from '../enums/rol.enum';
import { DOCUMENT } from '@angular/common';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.scss']
})

export class HeaderComponent implements OnInit {

	public isMenuCollapsed = true;
	private cssDarkUrl: string;
	private cssLightUrl: string;
	private cssDark: boolean;

	constructor(
		private loginService: AuthenticationService,
		private router: Router,
		@Inject(DOCUMENT) private document
	) {
		this.cssLightUrl = 'https://cdnjs.cloudflare.com/ajax/libs/bootswatch/4.3.1/flatly/bootstrap.min.css';
		this.cssDarkUrl = 'https://cdnjs.cloudflare.com/ajax/libs/bootswatch/4.3.1/slate/bootstrap.min.css';
		this.cssDark = false;
	}

	ngOnInit() {
	}

	toggleTheme() {
		this.document.getElementById('stylesheet').setAttribute('href', this.cssDark ? this.cssLightUrl : this.cssDarkUrl);
		this.cssDark = !this.cssDark;
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
		if(this.cssDark) this.toggleTheme();
		return this.loginService.logOut();
	}

	isCurrentRouter(router : string) {
		return this.router.isActive(router, true);
	}
}
