import { TestBed } from '@angular/core/testing';

import { ResourceLanguageService } from './resource-language-service.service';

describe('TrainerLanguageService', () => {
  let service: ResourceLanguageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResourceLanguageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
