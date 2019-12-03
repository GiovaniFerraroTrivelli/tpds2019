import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GlobalScriptsService {

  constructor() { }

	nroClienteFormat(nroCliente) {
		return nroCliente.substr(0, 2) + '-' + nroCliente.substr(2, 8);
	}

	removeHyphen(text) {
		if(text === null)
			return null;
		
		return text.split('-').join('');
	}

	parseMoney(text) {
		return text.substr(4);
	}
}
