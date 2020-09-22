import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourcesFormComponent } from './resources-form.component';

describe('ResourcesFormComponent', () => {
  let component: ResourcesFormComponent;
  let fixture: ComponentFixture<ResourcesFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResourcesFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResourcesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
