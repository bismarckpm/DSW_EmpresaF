import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudyRquestComponent } from './study-request.component';

describe('StudyRquestComponent', () => {
  let component: StudyRquestComponent;
  let fixture: ComponentFixture<StudyRquestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudyRquestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudyRquestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
