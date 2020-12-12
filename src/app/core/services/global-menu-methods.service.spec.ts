import { TestBed } from '@angular/core/testing';

import { GlobalMenuMethodsService } from './global-menu-methods.service';

describe('GlobalMenuMethodsService', () => {
  let service: GlobalMenuMethodsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GlobalMenuMethodsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
