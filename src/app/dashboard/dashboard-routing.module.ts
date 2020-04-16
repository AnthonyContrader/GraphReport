import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { UserRouteAccessService } from 'src/authJWT/user-route-access-service';
import { GraphComponent } from '../graph/graph.component';
import { HomeComponent } from './home/home.component';
import { DatasetComponent } from '../dataset/dataset.component';


const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent, canActivate: [ UserRouteAccessService ], children:[
    { path: '', component: HomeComponent, pathMatch: 'full' },
    { path: 'graph', component: GraphComponent },
    { path: 'ds', component: DatasetComponent }
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
