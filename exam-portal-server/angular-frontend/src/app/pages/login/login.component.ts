import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  loginData = {
    username: '',
    password: '',
  };

  constructor(private snack: MatSnackBar, private login: LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  formSubmit() {
    console.log('Logined');

    if (
      this.loginData.username.trim() == '' ||
      this.loginData.username == null
    ) {
      /*Swal.fire('Username is required','error'); */
      this.snack.open('Username is required !', 'Ok');
      return;
    }
    if (
      this.loginData.password.trim() == '' ||
      this.loginData.password == null
    ) {
      /* Swal.fire('Password is required','error'); */
      this.snack.open('Please enter your password here', 'Close');
      return;
    }
    //request server to generate token if uername and password is not null
    this.login.generateToken(this.loginData).subscribe(
      (data: any) => {
        console.log('Success');
        console.log(data);
        //login...
        this.login.loginUser(data.token);

        this.login.getCurrentUser().subscribe((user: any) => {
          this.login.setUser(user);
          console.log(user);
          //redirecting --> ADMIN .. admin-dashboard
          //redirecting --> NORMAL .. normal-dashboard
          if (this.login.getUserRole() === 'ADMIN') {
            //ADMIN Dashboard
            //window.location.href = '/admin';
            this.router.navigate(['admin']);
            this.login.loginStatusSubject.next(true);

          } else if (this.login.getUserRole() === 'NORMAL') {
            //NORMAL Dashboard
            // window.location.href = '/user-dashboard';
            this.router.navigate(['user-dashboard/0']);
            this.login.loginStatusSubject.next(true);
          } else {
            this.login.logout();
          }
        });
      },
      (error) => {
        console.log('Error!');
        console.log(error);
        this.snack.open("Invalid Details !! Try Again", 'Close')
      }
    );
  }
}
