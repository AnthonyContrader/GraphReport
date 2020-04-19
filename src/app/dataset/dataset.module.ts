import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { DatasetComponent } from './dataset.component';
import { DataSetModifyComponent } from './dataset-modify/dataset-modify.component';
import { ImportcsvComponent } from './importcsv/importcsv.component';


@NgModule({
  declarations: [
    DatasetComponent,
    DataSetModifyComponent,
    ImportcsvComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    FontAwesomeModule,
  ],
  providers: [],
  bootstrap: [DatasetComponent]
})
export class DatasetModule { }