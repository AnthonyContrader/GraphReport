import { NgModule } from "@angular/core";
import { UnitaComponent } from '../unitamisura/unita.component';
import { CategoriaComponent } from '../categoria/categoria.component';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [
    UnitaComponent,
    CategoriaComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [UnitaComponent]
})
export class UnitaModule { }
