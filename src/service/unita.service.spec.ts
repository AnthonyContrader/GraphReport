import { TestBed } from '@angular/core/testing';

import { UnitaService } from './unita.service';

describe('UnitaService', () => {
  let service: UnitaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UnitaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
