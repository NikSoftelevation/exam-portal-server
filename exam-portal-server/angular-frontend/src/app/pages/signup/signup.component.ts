import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/service/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  constructor(private userService: UserService, private snack: MatSnackBar) { }
  user = {
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    profile: '',
  };

  ngOnInit(): void { }

  formSubmit() {
    console.log(this.user);

    //validation for username
    if (this.user.username == '' || this.user.username == null) {
      this.snack.open('Username is required', 'ok', {
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'end',
      });
      return;
    }
    //validation for password
    if (this.user.password == '' || this.user.password == null) {
      this.snack.open('Password is required', 'ok', {
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'end',
      });
      return;
    }
    //validation for First Name
    if (this.user.firstName == '' || this.user.firstName == null) {
      this.snack.open('First name is a required field', 'ok', {
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'end',
      });
      return;
    }
    //validation for Last Name
    if (this.user.lastName == '' || this.user.lastName == null) {
      this.snack.open('Last name is a required field', 'ok', {
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'end',
      });
      return;
    }
    //validation for Email
    if (this.user.email == '' || this.user.email == null) {
      this.snack.open("Email can't be empty", 'ok', {
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'end',
      });
      return;
    }
    //validation for Phone
    if (this.user.phone == '' || this.user.phone == null) {
      this.snack.open("Phone number can't be null", 'ok', {
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'end',
      });
      return;
    }
    this.userService.addUser(this.user).subscribe(
      (data: any) => {
        console.log(data);
        //   this.snack.open('User registered successfully', 'ok', {
        //   duration: 3000});
        Swal.fire('User Registered Successfully', 'success');
      },
      (error) => {
        console.log(error);
        Swal.fire('Error', 'User already exists', 'error');
      });
  }
}
