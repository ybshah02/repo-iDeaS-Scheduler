import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegionModifyFormComponent } from './region-modify-form.component';

describe('RegionModifyFormComponent', () => {
  let component: RegionModifyFormComponent;
  let fixture: ComponentFixture<RegionModifyFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegionModifyFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegionModifyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
