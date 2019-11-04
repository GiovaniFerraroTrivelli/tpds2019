import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

	private counter : number = 0;

	constructor() { }

	ngOnInit() {
	}

	i() {
		this.counter++;
	}

	d() {
		this.counter--;
	}

	isHidden() {
		return !this.counter;
	}
}
