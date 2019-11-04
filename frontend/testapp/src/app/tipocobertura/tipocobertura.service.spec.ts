import { TestBed } from '@angular/core/testing';

import { TipocoberturaService } from './tipocobertura.service';

describe('TipocoberturaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TipocoberturaService = TestBed.get(TipocoberturaService);
    expect(service).toBeTruthy();
  });
});
