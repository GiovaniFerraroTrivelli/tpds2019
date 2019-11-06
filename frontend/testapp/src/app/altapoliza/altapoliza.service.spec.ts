import { TestBed } from '@angular/core/testing';

import { AltapolizaService } from './altapoliza.service';

describe('AltapolizaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AltapolizaService = TestBed.get(AltapolizaService);
    expect(service).toBeTruthy();
  });
});
