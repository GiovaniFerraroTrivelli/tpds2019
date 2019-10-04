import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscarpolizaComponent } from './buscarpoliza.component';

describe('BuscarpolizaComponent', () => {
  let component: BuscarpolizaComponent;
  let fixture: ComponentFixture<BuscarpolizaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuscarpolizaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuscarpolizaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
