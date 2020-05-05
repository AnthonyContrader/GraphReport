import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PresentazioneComponent } from './presentazione.component';

describe('PresentazioneComponent', () => {
  let component: PresentazioneComponent;
  let fixture: ComponentFixture<PresentazioneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PresentazioneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PresentazioneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
