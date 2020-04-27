import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { UserRouteAccessService } from 'src/authJWT/user-route-access-service';
import { GraphComponent } from '../graph/graph.component';
import { HomeComponent } from './home/home.component';
import { DatasetComponent } from '../dataset/dataset.component';
import { UnitaComponent } from '../unitamisura/unita.component';
import { ImportCsvComponent } from '../dataset/import-csv/import-csv.component';
import { GraphModifyComponent } from '../graph/graph-modify/graph-modify.component';
import { DataSetModifyComponent } from '../dataset/dataset-modify/dataset-modify.component';


const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent, canActivate: [ UserRouteAccessService ], children:[
    { path: '', component: HomeComponent, pathMatch: 'full' },
    { path: 'unita', component: UnitaComponent},
    { path: 'graph', component: GraphComponent },
    { path: 'ds', component: DatasetComponent },
    { path: 'datasetmodify', component: DataSetModifyComponent },
    { path: 'csv', component: ImportCsvComponent },
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
