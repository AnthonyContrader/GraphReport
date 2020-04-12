import { Injectable, isDevMode } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AccountService } from './account.service';
import { StateStorageService } from './state-storage.service';


@Injectable({ providedIn: 'root' })
export class UserRouteAccessService implements CanActivate {
    constructor(
        private router: Router,
            private accountService: AccountService,
            private stateStorageService: StateStorageService,
    ) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Promise<boolean> {
        const authorities = route.data['authorities'];
        // We need to call the checkLogin / and so the accountService.identity() function, to ensure,
        // that the client has a principal too, if they already logged in by the server.
        // This could happen on a page refresh.
        return this.checkLogin(authorities, state.url);
    }

    checkLogin(authorities: string[], url: string): boolean {
            
            if (this.accountService.isAuthenticated()) {
                    return true;
            }else{
                this.stateStorageService.storeUrl(url);
                this.router.navigate(['login']).then(() => {
                                
                //messaggio richiesta login

                });
                return false;
                
            }

    }
}
