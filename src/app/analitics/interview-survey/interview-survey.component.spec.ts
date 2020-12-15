import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InterviewSurveyComponent } from './interview-survey.component';

describe('InterviewSurveyComponent', () => {
  let component: InterviewSurveyComponent;
  let fixture: ComponentFixture<InterviewSurveyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InterviewSurveyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InterviewSurveyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
