import { TestBed } from '@angular/core/testing';

import { GlobalScriptsService } from './global-scripts.service';

describe('GlobalScriptsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GlobalScriptsService = TestBed.get(GlobalScriptsService);
    expect(service).toBeTruthy();
  });
});
