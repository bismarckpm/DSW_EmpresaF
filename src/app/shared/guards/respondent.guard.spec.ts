import { TestBed } from '@angular/core/testing';

import { RespondentGuard } from './respondent.guard';

describe('RespondentGuard', () => {
  let guard: RespondentGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(RespondentGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
