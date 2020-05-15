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
import { DatasetComponent } from '../dataset/dataset.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UnitaModule } from '../unitamisura/unita/unita.module';
import { ImportCsvComponent } from '../dataset/import-csv/import-csv.component';
import { DragNdropDirective } from 'src/directive/dragndrop.directive';
import { DataSetModifyComponent } from '../dataset/dataset-modify/dataset-modify.component';
import { UserComponent } from '../user/user.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProfiloComponent } from '../profilo/profilo.component';
import { PresentazioneModule } from '../presentazione/presentazione.module';
import { PasswordComponent } from '../password/password.component';
import { PasswordStrengthBarComponent } from '../password/password-strength-bar.component';
import { ExportCsvComponent } from '../dataset/export-csv/export-csv.component';
import { Angular2CsvModule } from 'angular2-csv';



@NgModule({
  declarations: [
    DashboardComponent,
    HeaderComponent,
    HomeComponent,
    NavComponent,
    DatasetComponent,
    ImportCsvComponent,
    ExportCsvComponent,
    DragNdropDirective,
    DataSetModifyComponent,
    UserComponent,
    ProfiloComponent,
    PasswordComponent,
    PasswordStrengthBarComponent
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    FontAwesomeModule,
    GraphModule,
    UnitaModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    NgbModule,
    PresentazioneModule,
    Angular2CsvModule
  ],
  providers: [
    UserRouteAccessService
  ],
  bootstrap: [DashboardComponent]
})
export class DashboardModule { }
