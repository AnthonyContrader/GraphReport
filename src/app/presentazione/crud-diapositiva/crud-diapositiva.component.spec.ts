import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudDiapositivaComponent } from './crud-diapositiva.component';

describe('CrudDiapositivaComponent', () => {
  let component: CrudDiapositivaComponent;
  let fixture: ComponentFixture<CrudDiapositivaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrudDiapositivaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrudDiapositivaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
