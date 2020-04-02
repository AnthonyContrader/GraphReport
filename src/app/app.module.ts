import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginModule } from './login/login.module';
import { HttpClientModule } from '@angular/common/http';
import { LayoutModule } from './layout/layout.module';
import { CategorieComponent } from './categoria/categorie.component';
import { UnitamisureComponent } from './unitamisura/unitamisure.component';
import { UtenteModule } from './utente/utente.module';
import { AdminModule } from './admin/admin.module';
import { DatasetComponent } from './dataset/dataset.component';
import { DataSetModifyComponent } from './dataset/dataset-modify/dataset-modify.component';
import { GraphComponent } from './graph/graph.component';
import { ChartsModule } from 'ng2-charts';
import { RegisterComponent } from './register/register.component';
import { GraphModifyComponent } from './graph/graph-modify/graph-modify.component';
import { SearchComponent } from './search/search.component';
import { ImportcsvComponent } from './dataset/importcsv/importcsv.component';

/**
 * Modulo principale dell'applicazione. Qui vengono importati i moduli secondari. L'UNICA component
 * da dichiare qui è l'AppComponent, tutte le altre devono essere dichiarate nel loro modulo e questo importato
 * qui.
 *
 * @author Vittorio Valent
*/
@NgModule({
  declarations: [
    AppComponent,
    CategorieComponent,
    UnitamisureComponent,
    DatasetComponent,
    DataSetModifyComponent,
    GraphComponent,
    GraphModifyComponent,
    RegisterComponent,
    SearchComponent,
    ImportcsvComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LoginModule,
    HttpClientModule,
    LayoutModule,
    UtenteModule,
    AdminModule,
    FormsModule,
    ReactiveFormsModule,
    ChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
