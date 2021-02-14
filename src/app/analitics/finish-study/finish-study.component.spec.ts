import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinishStudyComponent } from './finish-study.component';

describe('FinishStudyComponent', () => {
  let component: FinishStudyComponent;
  let fixture: ComponentFixture<FinishStudyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinishStudyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinishStudyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
