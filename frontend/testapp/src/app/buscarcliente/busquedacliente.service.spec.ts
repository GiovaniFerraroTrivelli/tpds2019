import { TestBed } from '@angular/core/testing';

import { BusquedaclienteService } from './busquedacliente.service';

describe('BusquedaclienteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BusquedaclienteService = TestBed.get(BusquedaclienteService);
    expect(service).toBeTruthy();
  });
});
