import { TestBed } from '@angular/core/testing';

import { ConsultarclientesService } from './consultarclientes.service';

describe('ConsultarclientesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ConsultarclientesService = TestBed.get(ConsultarclientesService);
    expect(service).toBeTruthy();
  });
});
