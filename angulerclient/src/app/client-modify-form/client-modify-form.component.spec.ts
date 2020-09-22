import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientModifyFormComponent } from './client-modify-form.component';

describe('ClientModifyFormComponent', () => {
  let component: ClientModifyFormComponent;
  let fixture: ComponentFixture<ClientModifyFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientModifyFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientModifyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
