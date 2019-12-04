import { NgForm, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';

import { AuthenticationService } from '../authentication/authentication.service';
import { UserLogin } from './user-login';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

	invalidLogin = false
	loginForm: FormGroup;
	error = "";

	public title = "Iniciar sesión";

	constructor(
		private router: Router,
		private loginService: AuthenticationService,
		private titleService: Title
	) { }

	ngOnInit() {
		if(this.loginService.isUserLoggedIn()) {
			this.router.navigate(['']);
		}

		this.titleService.setTitle(this.title);

		this.loginForm = new FormGroup({
			'nombreUsuario': new FormControl(null, Validators.required),
			'password': new FormControl(null, Validators.required),
		});
	}

	get nombreUsuario() { return this.loginForm.get('nombreUsuario'); }
	get password() { return this.loginForm.get('password'); }

	onSubmit(f : NgForm) {
		this.loginService.authenticate(f.value).subscribe(
			result => {
				sessionStorage.clear();
				
				sessionStorage.setItem('nombreUsuario', result.nombreUsuario);
				sessionStorage.setItem('nombre', result.nombre);
				sessionStorage.setItem('apellido', result.apellido);
				sessionStorage.setItem('email', result.email);
				sessionStorage.setItem('rol', result.rol);

				console.log(result);
				this.router.navigate(['']);
				this.invalidLogin = false;
			},
		    err => {
		    	console.log(err);
				this.invalidLogin = true;
		    	this.error = err.error.mensaje;
		    }
		);
	}

}
