import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ActivateComponent } from './activate/activate.component';
import { PasswordResetInitComponent } from './password-reset/init/password-reset-init.component';
import { PasswordResetFinishComponent } from './password-reset/finish/password-reset-finish.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'password-reset-init', component: PasswordResetInitComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'activate', component: ActivateComponent},
  { path: 'reset/finish', component: PasswordResetFinishComponent},
  { path: '', redirectTo: '/dashboard', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
