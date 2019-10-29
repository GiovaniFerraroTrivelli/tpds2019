import { TestBed } from '@angular/core/testing';

import { GeografiaService } from './geografia.service';

describe('GeografiaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GeografiaService = TestBed.get(GeografiaService);
    expect(service).toBeTruthy();
  });
});
