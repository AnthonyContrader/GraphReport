import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { UserRouteAccessService } from 'src/authJWT/user-route-access-service';


const routes: Routes = [
  {  path: '', component: DashboardComponent, pathMatch: 'full', canActivate: [ UserRouteAccessService ] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
