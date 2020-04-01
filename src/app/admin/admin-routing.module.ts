import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UsersComponent } from './users/users.component';
import { CategorieComponent } from '../categoria/categorie.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import { DatasetComponent } from '../dataset/dataset.component';
import { DataSetModifyComponent } from '../dataset/dataset-modify/dataset-modify.component';
import { UnitamisureComponent } from '../unitamisura/unitamisure.component';
import { GraphComponent } from '../graph/graph.component';
import { GraphModifyComponent } from '../graph/graph-modify/graph-modify.component';


const routes: Routes = [
  { path: 'admin-dashboard', component: AdminLayoutComponent, children: [
    { path: '', component: AdminDashboardComponent},
    { path: 'users', component: UsersComponent},
    { path: 'work-in-progress', component: WorkInProgressComponent},
    { path: 'dataset', component: DatasetComponent},
    { path: 'datasetmodify', component: DataSetModifyComponent},
    { path: 'categorie', component: CategorieComponent},
    { path: 'unitamisure', component: UnitamisureComponent},
    { path: 'graph', component: GraphComponent},
    { path: 'graphModify/:id/:owner', component: GraphModifyComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
