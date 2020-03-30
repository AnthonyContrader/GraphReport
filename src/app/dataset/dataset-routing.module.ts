import { NgModule } from "@angular/core";
import { Routes, RouterModule } from '@angular/router';
import { DatasetComponent } from './dataset.component';
import { DataSetModifyComponent } from './dataset-modify/dataset-modify.component';

const route : Routes = [
    {path: 'dataset', component: DatasetComponent, children:[
        {path: 'dataset-modify', component: DataSetModifyComponent}
    ]}
]

@NgModule({
    imports:[RouterModule.forChild(route)],
    exports:[RouterModule]
})
export class DatasetRoutingModule{}