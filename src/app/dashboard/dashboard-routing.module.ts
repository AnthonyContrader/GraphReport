import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { UserRouteAccessService } from 'src/authJWT/user-route-access-service';
import { GraphComponent } from '../graph/graph.component';
import { HomeComponent } from './home/home.component';


const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent, canActivate: [ UserRouteAccessService ], children:[
    { path: '', component: HomeComponent, pathMatch: 'full' },
    { path: 'graph', component: GraphComponent },
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
