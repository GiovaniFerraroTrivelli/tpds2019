import { TestBed } from '@angular/core/testing';

import { RegistrarPagoService } from './registrar-pago.service';

describe('RegistrarPagoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RegistrarPagoService = TestBed.get(RegistrarPagoService);
    expect(service).toBeTruthy();
  });
});
