import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourcesModifyFormComponent } from './resources-modify-form.component';

describe('ResourcesModifyFormComponent', () => {
  let component: ResourcesModifyFormComponent;
  let fixture: ComponentFixture<ResourcesModifyFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResourcesModifyFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResourcesModifyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
