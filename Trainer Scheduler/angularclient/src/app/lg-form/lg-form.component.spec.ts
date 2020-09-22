import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LgFormComponent } from './lg-form.component';

describe('LgFormComponent', () => {
  let component: LgFormComponent;
  let fixture: ComponentFixture<LgFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LgFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LgFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
