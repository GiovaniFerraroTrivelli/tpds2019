import { TestBed } from '@angular/core/testing';

import { HijosService } from './hijos.service';

describe('HijosService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HijosService = TestBed.get(HijosService);
    expect(service).toBeTruthy();
  });
});
