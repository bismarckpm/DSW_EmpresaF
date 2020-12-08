import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GlobalMenuPComponent } from './global-menuP.component';

describe('GlobalMenuComponent', () => {
  let component: GlobalMenuPComponent;
  let fixture: ComponentFixture<GlobalMenuPComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GlobalMenuPComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GlobalMenuPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
