import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { NavComponent } from './nav/nav.component';
import { UserRouteAccessService } from 'src/authJWT/user-route-access-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { GraphModule } from '../graph/graph.module';
import { UnitaModule } from '../unita/unita.module';
import { DatasetComponent } from '../dataset/dataset.component';
import { FormsModule } from '@angular/forms';




@NgModule({
  declarations: [
    DashboardComponent,
    HeaderComponent,
    HomeComponent,
    NavComponent,
    DatasetComponent,
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    FontAwesomeModule,
    GraphModule,
    UnitaModule,
    FormsModule
  ],
  providers: [
    UserRouteAccessService
  ],
  bootstrap: [DashboardComponent]
})
export class DashboardModule { }
