import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestStudyComponent } from './request-study.component';

describe('RequestStudyComponent', () => {
  let component: RequestStudyComponent;
  let fixture: ComponentFixture<RequestStudyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RequestStudyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestStudyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
