import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LsFormComponent } from './ls-form.component';

describe('LsFormComponent', () => {
  let component: LsFormComponent;
  let fixture: ComponentFixture<LsFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LsFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
