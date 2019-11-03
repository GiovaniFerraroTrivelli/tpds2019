import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DialogComponent } from './dialog.component';

@Injectable()
export class DialogService {

  constructor(private modalService: NgbModal) { }

  public confirm(
    title: string,
    message: string,
    confirmButtons: boolean = false,
    btnOkText: string = 'Aceptar',
    btnCancelText: string = 'Cancelar',
    dialogSize: 'sm'|'lg' = 'sm'): Promise<boolean> {
    const modalRef = this.modalService.open(DialogComponent, { size: dialogSize });
    modalRef.componentInstance.title = title;
    modalRef.componentInstance.message = message;
    modalRef.componentInstance.btnOkText = btnOkText;
    modalRef.componentInstance.btnCancelText = btnCancelText;
    modalRef.componentInstance.confirmButtons = confirmButtons;

    return modalRef.result;
  }

  public alert(
    title: string,
    message: string,
    confirmButtons: boolean = false,
    btnOkText: string = 'Cerrar',
    dialogSize: 'sm'|'lg' = 'lg'): Promise<boolean> {
    const modalRef = this.modalService.open(DialogComponent, { size: dialogSize });
    modalRef.componentInstance.title = title;
    modalRef.componentInstance.message = message;
    modalRef.componentInstance.btnOkText = btnOkText;
    modalRef.componentInstance.confirmButtons = confirmButtons;

    return modalRef.result;
  }

}
