import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourcesDisplayComponent } from './resources-display.component';

describe('ResourcesDisplayComponent', () => {
  let component: ResourcesDisplayComponent;
  let fixture: ComponentFixture<ResourcesDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResourcesDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResourcesDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
