import { Component, OnInit } from '@angular/core';
import { JhiEventManager } from 'ng-jhipster';
import { LoginService } from 'src/authJWT/login.service';
import { Router } from '@angular/router';
import { StateStorageService } from 'src/authJWT/state-storage.service';
import { faTimes } from '@fortawesome/free-solid-svg-icons';
import { UserDTO } from 'src/dto/user.dto';
import { UserService } from 'src/service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  authenticationError: number = 0;
  password: string;
  rememberMe: boolean = false;
  username: string;
  credentials: any;
  newUser: UserDTO;

  faTimes = faTimes;

  constructor(
    private eventManager: JhiEventManager,
    private stateStorageService: StateStorageService,
    private loginService: LoginService,
    private router: Router,
    private userService: UserService
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
                rememberMe: this.rememberMe
            })
            .then(() => {
                this.authenticationError = 0;

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
                this.authenticationError = 1;
            });
  }

  resetErr(){
    this.authenticationError = 0;
  }

  register(): void {
    this.newUser = new UserDTO();

    this.userService.create(this.newUser);

    this.router.navigate(['/register']);
  }

  reset(): void {
    this.router.navigate(['/password-reset-init']);
  }

}
