import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnitaComponent } from './unita.component';

describe('UnitaComponent', () => {
  let component: UnitaComponent;
  let fixture: ComponentFixture<UnitaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnitaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnitaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
