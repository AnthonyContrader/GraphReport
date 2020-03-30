import { DatasetComponent } from './dataset.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DataSetModifyComponent } from './dataset-modify/dataset-modify.component';
import { DatasetRoutingModule } from './dataset-routing.module';


@NgModule({
    declarations: [DatasetComponent, DataSetModifyComponent],
    imports:[
        DatasetRoutingModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule
    ]
})
export class DatasetModule{}