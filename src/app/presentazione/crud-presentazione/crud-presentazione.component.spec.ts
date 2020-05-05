import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudPresentazioneComponent } from './crud-presentazione.component';

describe('CrudPresentazioneComponent', () => {
  let component: CrudPresentazioneComponent;
  let fixture: ComponentFixture<CrudPresentazioneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrudPresentazioneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrudPresentazioneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
