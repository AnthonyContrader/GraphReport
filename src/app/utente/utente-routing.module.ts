import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UtenteLayoutComponent } from '../layout/utente-layout/utente-layout.component';
import { UtenteDashboardComponent } from './utente-dashboard/utente-dashboard.component';
import { UsersComponent } from './users/users.component';
import { CategorieComponent } from '../categoria/categorie.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import { DatasetComponent } from '../dataset/dataset.component';
import { UnitamisureComponent } from '../unitamisura/unitamisure.component';
import { DataSetModifyComponent } from '../dataset/dataset-modify/dataset-modify.component';
import { GraphComponent } from '../graph/graph.component';
import { GraphModifyComponent } from '../graph/graph-modify/graph-modify.component';
import { SearchComponent } from '../search/search.component';
import { ImportcsvComponent } from '../dataset/importcsv/importcsv.component';


const routes: Routes = [
  { path: 'utente-dashboard', component: UtenteLayoutComponent, children: [
    { path: '', component: UtenteDashboardComponent},
    { path: 'users', component: UsersComponent},
    { path: 'work-in-progress', component: WorkInProgressComponent},
    { path: 'dataset', component: DatasetComponent},
    { path: 'datasetmodify', component: DataSetModifyComponent},
    { path: 'categorie', component: CategorieComponent},
    { path: 'unitamisure', component: UnitamisureComponent},
    { path: 'graph', component: GraphComponent},
    { path: 'graphModify/:id/:owner', component: GraphModifyComponent},
    { path: 'search/:ricerca', component: SearchComponent},
    { path: 'importcsv', component: ImportcsvComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UtenteRoutingModule { }
