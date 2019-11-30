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

	public title = "Iniciar sesión";

	constructor(
		private router: Router,
		private loginService: AuthenticationService,
		private titleService: Title
	) { }

	ngOnInit() {
		this.titleService.setTitle(this.title);

		this.loginForm = new FormGroup({
			'username': new FormControl(null, Validators.required),
			'password': new FormControl(null, Validators.required),
		});
	}

	get username() { return this.loginForm.get('username'); }
	get password() { return this.loginForm.get('password'); }

	onSubmit(f : NgForm) {
		this.loginService.authenticate(f.value).subscribe(result => {
				if(result)
				{
					sessionStorage.setItem('login', JSON.stringify(result));
					console.log(result);
					this.router.navigate(['']);
					this.invalidLogin = false;
				}
				else
				{
					this.invalidLogin = true;
				}
			}
		);
	}

}
