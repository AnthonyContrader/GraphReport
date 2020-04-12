import { Component, OnInit } from '@angular/core';
import { JhiEventManager } from 'ng-jhipster';
import { LoginService } from 'src/authJWT/login.service';
import { Router } from '@angular/router';
import { StateStorageService } from 'src/authJWT/state-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  authenticationError: boolean;
  password: string;
  rememberMe: boolean;
  username: string;
  credentials: any;

  constructor(
    private eventManager: JhiEventManager,
    private stateStorageService: StateStorageService,
    private loginService: LoginService,
    private router: Router,
  ) { 
    this.credentials = {};
   }

  ngOnInit(): void {
  }

  login(){
    this.loginService
            .login({
                username: this.username,
                password: this.password,
                rememberMe: false
            })
            .then(() => {
                this.authenticationError = false;
                
                this.eventManager.broadcast({
                    name: 'authenticationSuccess',
                    content: 'Sending Authentication Success'
                });
                // previousState was set in the authExpiredInterceptor before being redirected to login modal.
                // since login is successful, go to stored previousState and clear previousState
                const redirect = this.stateStorageService.getUrl();
                
                this.stateStorageService.storeUrl('');
                console.warn('Login success: redirect to: '+redirect)
                this.router.navigate([redirect]);
                
            })
            .catch(() => {
                this.authenticationError = true;
                alert("errore");
            });
  }

}
