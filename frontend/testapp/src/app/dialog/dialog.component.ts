import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
	selector: 'app-dialog',
	templateUrl: './dialog.component.html',
})

export class DialogComponent implements OnInit {

	@Input() title: string;
	@Input() message: string;
	@Input() btnOkText: string;
	@Input() btnCancelText: string;
	@Input() confirmButtons: boolean;

	constructor(private activeModal: NgbActiveModal) { }

	ngOnInit() {
	}

	public decline() {
		this.activeModal.close(false);
	}

	public accept() {
		this.activeModal.close(true);
	}

	public dismiss() {
		this.activeModal.dismiss();
	}
}
