import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RenovatedStudyComponent } from './renovated-study.component';

describe('RenovatedStudyComponent', () => {
  let component: RenovatedStudyComponent;
  let fixture: ComponentFixture<RenovatedStudyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RenovatedStudyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RenovatedStudyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
