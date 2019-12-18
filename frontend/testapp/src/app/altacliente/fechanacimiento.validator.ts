import { FormControl } from '@angular/forms';

export function FechaNacimientoValidator(c: FormControl) {
	const today = new Date();
	const fechaNac = new Date(c.value);
	let age = today.getFullYear() - fechaNac.getFullYear();
	const m = today.getMonth() - fechaNac.getMonth();

	if(m < 0 || (m === 0 && today.getDate() < fechaNac.getDate())) {
	  age--;
	}
	
  	return age >= 18 ? null : {
    	validateAge: {
      		valid: false
    	}
  	};
}
