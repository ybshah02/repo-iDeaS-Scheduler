import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LsModifyFormComponent } from './ls-modify-form.component';

describe('LsModifyFormComponent', () => {
  let component: LsModifyFormComponent;
  let fixture: ComponentFixture<LsModifyFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LsModifyFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LsModifyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
