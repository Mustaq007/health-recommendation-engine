import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {


  errorMessage: string = '';

  email:string='';
  password:string='';

  constructor(
    
     private http: HttpClient,
     private router: Router
  ) {
   
  }

  login() {

      this.http.post<any>('http://localhost:8000/auth/login', {emailId:this.email,password:this.password})
        .subscribe(
          response => {
            if (response.role) {
              localStorage.setItem('userRole', response.role);
              localStorage.setItem('emailId', this.email);
              
              if (response.role === 'admin') {
                this.router.navigate(['/admin-dashboard']);
              } else if (response.role === 'doctor') {
                this.router.navigate(['/doctor-dashboard']);
              } else {
                this.router.navigate(['/user-dashboard']);
              }
            } else {
              this.errorMessage = response.message;
            }
          },
          error => {
            this.errorMessage = 'Invalid email or password';
          }
        );
  }

}
