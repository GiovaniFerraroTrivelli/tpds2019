import { TestBed } from '@angular/core/testing';

import { BuscarpolizaService } from './buscarpoliza.service';

describe('BuscarpolizaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BuscarpolizaService = TestBed.get(BuscarpolizaService);
    expect(service).toBeTruthy();
  });
});
