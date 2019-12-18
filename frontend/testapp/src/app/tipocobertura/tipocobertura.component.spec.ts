import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TipocoberturaComponent } from './tipocobertura.component';

describe('TipocoberturaComponent', () => {
  let component: TipocoberturaComponent;
  let fixture: ComponentFixture<TipocoberturaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TipocoberturaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TipocoberturaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
