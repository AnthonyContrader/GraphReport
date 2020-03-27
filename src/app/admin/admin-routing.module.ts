import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UsersComponent } from './users/users.component';
//import { CategoriaComponent } from './categoria/categoria.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import { DatasetComponent } from '../dataset/dataset.component';


const routes: Routes = [
  { path: 'admin-dashboard', component: AdminLayoutComponent, children: [
    { path: '', component: AdminDashboardComponent},
    { path: 'users', component: UsersComponent},
    { path: 'work-in-progress', component: WorkInProgressComponent},
    { path: 'dataset', component: DatasetComponent},
//    { path: 'categoria', component: CategoriaComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
