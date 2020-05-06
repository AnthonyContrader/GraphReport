import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CrudDiapositivaComponent } from './crud-diapositiva/crud-diapositiva.component';
import { CrudPresentazioneComponent } from './crud-presentazione/crud-presentazione.component';


@NgModule({
  declarations : [
    CrudDiapositivaComponent,
    CrudPresentazioneComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    FontAwesomeModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [PresentazioneModule]
})
export class PresentazioneModule { }