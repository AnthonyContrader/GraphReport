import { TestBed } from '@angular/core/testing';

import { UnitamisuraService } from '../service/unitamisura.service';

describe('UnitamisuraService', () => {
  let service: UnitamisuraService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UnitamisuraService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
