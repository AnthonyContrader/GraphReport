import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnitamisureComponent } from './unitamisure.component';

describe('UnitamisuraComponent', () => {
  let component: UnitamisureComponent;
  let fixture: ComponentFixture<UnitamisureComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnitamisureComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnitamisureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
