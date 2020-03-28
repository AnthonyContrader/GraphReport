import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { UtenteRoutingModule } from './utente-routing.module';
import { UtenteDashboardComponent } from './utente-dashboard/utente-dashboard.component';
import { UsersComponent } from './users/users.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';

/**
 * Modulo dell'utente, qui vengono dichiarate le component che utilizza 
 * l'utente. Questo modulo importa UtenteRoutingModule.
 * 
 * @author Piero :P
 * 
 * @see UtenteRoutingModule
 */
@NgModule({
  declarations: [UtenteDashboardComponent, UsersComponent, WorkInProgressComponent],
  imports: [
    CommonModule,
    UtenteRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class UtenteModule { }
