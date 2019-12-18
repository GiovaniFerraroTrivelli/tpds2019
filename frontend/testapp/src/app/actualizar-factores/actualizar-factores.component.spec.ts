import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualizarFactoresComponent } from './actualizar-factores.component';

describe('ActualizarFactoresComponent', () => {
  let component: ActualizarFactoresComponent;
  let fixture: ComponentFixture<ActualizarFactoresComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActualizarFactoresComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActualizarFactoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
