import { TestBed } from '@angular/core/testing';

import { PrivatelessonsService } from './privatelessons.service';

describe('PrivatelessonsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PrivatelessonsService = TestBed.get(PrivatelessonsService);
    expect(service).toBeTruthy();
  });
});
