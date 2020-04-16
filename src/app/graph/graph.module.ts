import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { GraphComponent } from './graph.component';
import { GraphListComponent } from './graph-list/graph-list.component';
import { GraphCreateComponent } from './graph-create/graph-create.component';
import { GraphModifyComponent } from './graph-modify/graph-modify.component';
import { GraphDrawComponent } from './graph-modify/graph-draw/graph-draw.component';
import { ChartsModule } from 'ng2-charts';

@NgModule({
  declarations: [
    GraphComponent,
    GraphListComponent,
    GraphCreateComponent,
    GraphModifyComponent,
    GraphDrawComponent,    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    FontAwesomeModule,
    ChartsModule
  ],
  providers: [],
  bootstrap: [GraphComponent]
})
export class GraphModule { }