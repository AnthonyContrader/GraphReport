import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminLayoutComponent } from './admin-layout/admin-layout.component';
import { AdminMenuComponent } from './admin-layout/admin-menu/admin-menu.component';
import { HeaderComponent } from './header/header.component';
import { RouterModule } from '@angular/router';
import { UtenteMenuComponent } from './utente-layout/utente-menu/utente-menu.component';
import { UtenteLayoutComponent } from './utente-layout/utente-layout.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

/**
 * Modulo di layout. Viene caricato nel rputer outlet padre e poi
 * non viene più ricaricato. Quando clicchiamo su un link ricarichiamo solo l'outlet
 * che sta dentro AdminLayoutComponent
 *
 * @author Vittorio Valent
 *
 * @see AdminLayoutComponent
 */
@NgModule({
  declarations: [AdminLayoutComponent, AdminMenuComponent, UtenteLayoutComponent, UtenteMenuComponent,  HeaderComponent],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class LayoutModule { }
