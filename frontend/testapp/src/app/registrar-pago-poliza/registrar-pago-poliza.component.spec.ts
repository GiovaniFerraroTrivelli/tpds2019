import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarPagoPolizaComponent } from './registrar-pago-poliza.component';

describe('RegistrarPagoPolizaComponent', () => {
  let component: RegistrarPagoPolizaComponent;
  let fixture: ComponentFixture<RegistrarPagoPolizaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistrarPagoPolizaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrarPagoPolizaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
