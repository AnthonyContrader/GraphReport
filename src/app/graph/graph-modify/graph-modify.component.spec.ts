import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GraphModifyComponent } from './graph-modify.component';

describe('GraphModifyComponent', () => {
  let component: GraphModifyComponent;
  let fixture: ComponentFixture<GraphModifyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GraphModifyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GraphModifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
