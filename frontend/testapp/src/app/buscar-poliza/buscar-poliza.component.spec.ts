import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscarPolizaComponent } from './buscar-poliza.component';

describe('BuscarPolizaComponent', () => {
  let component: BuscarPolizaComponent;
  let fixture: ComponentFixture<BuscarPolizaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuscarPolizaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuscarPolizaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
