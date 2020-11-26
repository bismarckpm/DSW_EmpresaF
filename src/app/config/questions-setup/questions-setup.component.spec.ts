import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionsSetupComponent } from './questions-setup.component';

describe('QuestionsSetupComponent', () => {
  let component: QuestionsSetupComponent;
  let fixture: ComponentFixture<QuestionsSetupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuestionsSetupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionsSetupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
