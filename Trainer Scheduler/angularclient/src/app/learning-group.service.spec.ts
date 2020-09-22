import { TestBed } from '@angular/core/testing';

import { LearningGroupService } from './learning-group.service';

describe('LearningGroupService', () => {
  let service: LearningGroupService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LearningGroupService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
