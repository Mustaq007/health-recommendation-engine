import { CommonModule, NgIf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';


interface UserDetails {
  id: number;
  name: string;
  dob: string;
  contactNumber: string;
  address: string;
  gender: string;
  emailAddress: string;
  age: string;
  medicalHistory: string;
  riskRecommendation: string;
  personalizedRecommendation: string;
  doctorsRecommendation: string;
  requireConsultation:boolean;
}



@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  imports: [CommonModule,NgIf, FormsModule],
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.scss'
})
export class UserDashboardComponent implements OnInit {
  userData: UserDetails | null = null;
  email: string = '';
  errorMessage: string = '';
  emailId: string | null = '';
  consultMessage:string | null = '';
  consult:boolean=false;

  constructor(private http: HttpClient,private router: Router) {}

  ngOnInit(): void {
    // if (typeof window !== 'undefined' && localStorage) {
       const email = localStorage.getItem('emailId');
      // console.log(email);
      this.fetchUserDetails(email);
    // }
    // this.emailId = localStorage.getItem('emailId');
    
  }

  fetchUserDetails(emailId:any) {
    // if (!this.email) {
    //   this.errorMessage = 'Please enter your email address';
    //   return;
    // }

    this.http.get<UserDetails>(`http://localhost:8000/api/users/details/${emailId}`)
      .subscribe({
        next: (data) => {
          this.userData = data;
          this.errorMessage = '';
        },
        error: () => {
          this.errorMessage = 'User not found or error fetching data';
          this.userData = null;
        }
      });
      // localStorage.clear();
  }


  navigateToLogin(){
    this.router.navigate(['/login']);  
  }
  scheduleAppointMent(){
    this.consult=true;
    this.consultMessage = 'Your Application is processed you will get further update via email'
    this.updateUserData();

  }

  updateUserData() {
    this.errorMessage = ''; // Reset error message
    if(this.userData){
      this.userData.requireConsultation=true;
    }
    this.http.patch('http://localhost:8000/api/users/update', this.userData)
      .subscribe(response => {
        console.log('User saved successfully', response);
        // this.isSubmitted = true;
        this.errorMessage = ''; // Clear error if successfully saved
      }, error => {
        console.error('Error saving user', error);
        this.errorMessage = error.error;
      });
  }


}