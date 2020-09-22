import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CapabilitiesDisplayComponent } from './capabilities-display.component';

describe('CapabilitiesDisplayComponent', () => {
  let component: CapabilitiesDisplayComponent;
  let fixture: ComponentFixture<CapabilitiesDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CapabilitiesDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CapabilitiesDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
