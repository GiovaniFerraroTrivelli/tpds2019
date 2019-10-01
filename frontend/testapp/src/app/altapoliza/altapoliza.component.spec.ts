import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AltapolizaComponent } from './altapoliza.component';

describe('AltapolizaComponent', () => {
  let component: AltapolizaComponent;
  let fixture: ComponentFixture<AltapolizaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AltapolizaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AltapolizaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
