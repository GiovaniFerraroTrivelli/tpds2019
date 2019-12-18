import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerarInformeMensualComponent } from './generar-informe-mensual.component';

describe('GenerarInformeMensualComponent', () => {
  let component: GenerarInformeMensualComponent;
  let fixture: ComponentFixture<GenerarInformeMensualComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GenerarInformeMensualComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GenerarInformeMensualComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
