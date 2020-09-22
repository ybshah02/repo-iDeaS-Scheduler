import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LgModifyFormComponent } from './lg-modify-form.component';

describe('LgModifyFormComponent', () => {
  let component: LgModifyFormComponent;
  let fixture: ComponentFixture<LgModifyFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LgModifyFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LgModifyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
