import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarclientesComponent } from './consultarclientes.component';

describe('ConsultarclientesComponent', () => {
  let component: ConsultarclientesComponent;
  let fixture: ComponentFixture<ConsultarclientesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultarclientesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarclientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
