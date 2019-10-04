import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { UserLogin } from '../user-login';
import { Title } from '@angular/platform-browser';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

	username = ''
	password = ''
	invalidLogin = false

	userLogin = new UserLogin();

	constructor(private router: Router,
	private loginservice: AuthenticationService,
	private titleService: Title) { }

	ngOnInit() {
		this.titleService.setTitle("Iniciar sesión");
	}

	checkLogin() {
		let pepito = this.loginservice.authenticate(this.userLogin);

		pepito.subscribe(result => {
				if(result)
				{
					sessionStorage.setItem('username', this.userLogin.username)
					this.router.navigate([''])
					this.invalidLogin = false
				}
				else
				{
					this.invalidLogin = true
				}
			}
		);

		return false;
	}
}