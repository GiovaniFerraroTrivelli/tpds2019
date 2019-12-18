import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarpolizaComponent } from './consultarpoliza.component';

describe('ConsultarpolizaComponent', () => {
  let component: ConsultarpolizaComponent;
  let fixture: ComponentFixture<ConsultarpolizaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultarpolizaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarpolizaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
