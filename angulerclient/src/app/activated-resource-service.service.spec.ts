import { TestBed } from '@angular/core/testing';

import { ActivatedResourceService } from './activated-resource-service.service';

describe('ActivatedResourceServiceService', () => {
  let service: ActivatedResourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActivatedResourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
