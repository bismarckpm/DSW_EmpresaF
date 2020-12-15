import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignStudyComponent } from './assign-study.component';

describe('AssignStudyComponent', () => {
  let component: AssignStudyComponent;
  let fixture: ComponentFixture<AssignStudyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignStudyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignStudyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
