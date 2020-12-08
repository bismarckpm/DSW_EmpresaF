import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { GlobalMenuAComponent } from './global-menuA.component';

describe('GlobalMenuComponent', () => {
  let component: GlobalMenuAComponent;
  let fixture: ComponentFixture<GlobalMenuAComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GlobalMenuAComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GlobalMenuAComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
