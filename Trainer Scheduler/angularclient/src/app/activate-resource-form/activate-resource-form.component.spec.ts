import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivateResourceFormComponent } from './activate-resource-form.component';

describe('ActivateResourceFormComponent', () => {
  let component: ActivateResourceFormComponent;
  let fixture: ComponentFixture<ActivateResourceFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivateResourceFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivateResourceFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
