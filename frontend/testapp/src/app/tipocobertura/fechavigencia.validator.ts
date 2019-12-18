import { FormControl } from '@angular/forms';

export function FechaVigenciaValidator(c: FormControl) {

	let today = new Date();
	let tomorrow = new Date();
	let oneMonth = new Date();
	let selectedDate = new Date(c.value);

	tomorrow.setDate(today.getDate() + 1);
	oneMonth.setMonth(today.getMonth() + 1);

  	return (selectedDate.getTime() <= oneMonth.getTime() && selectedDate.getTime() > today.getTime()) ? null : {
    	validateDate: {
      		valid: false
    	}
  	};
}
