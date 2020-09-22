import { TestBed } from '@angular/core/testing';

import { LearningSessionService } from './learning-session.service';

describe('LearningSessionService', () => {
  let service: LearningSessionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LearningSessionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
