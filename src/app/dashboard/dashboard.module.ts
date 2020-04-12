import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { NavComponent } from './nav/nav.component';
import { UserRouteAccessService } from 'src/authJWT/user-route-access-service';


@NgModule({
  declarations: [
    DashboardComponent,
    HeaderComponent,
    HomeComponent,
    NavComponent
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
  ],
  providers: [
    UserRouteAccessService
  ],
  bootstrap: [DashboardComponent]
})
export class DashboardModule { }