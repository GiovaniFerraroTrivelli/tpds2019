import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscarclienteComponent } from './Buscarcliente.component';

describe('BuscarclienteComponent', () => {
  let component: BuscarclienteComponent;
  let fixture: ComponentFixture<BuscarclienteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuscarclienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuscarclienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
