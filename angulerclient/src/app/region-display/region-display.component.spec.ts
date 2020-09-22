import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegionDisplayComponent } from './region-display.component';

describe('RegionDisplayComponent', () => {
  let component: RegionDisplayComponent;
  let fixture: ComponentFixture<RegionDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegionDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegionDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
