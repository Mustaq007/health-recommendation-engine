import { CommonModule, NgIf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule,NgIf],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.scss'
})
export class AdminDashboardComponent {

  user = {
    name: '',
    dob: '',
    contactNumber: '',
    address: '',
    gender: '',
    emailAddress: '',
    age: '',
    medicalHistory: '',
    riskRecommendation: '',
    personalizedRecommendation: '',
    doctorsRecommendation: '',
    password:'',
    lifeStyleHabit:''
  };


  isSubmitted = false;
  errorMessage: string = '';  // Store error message

  constructor(private http: HttpClient,private router: Router) {}

  saveUser() {
    this.errorMessage = ''; // Reset error message
    this.http.post('http://localhost:8000/api/users/save', this.user)
      .subscribe(response => {
        console.log('User saved successfully', response);
        this.isSubmitted = true;
        this.errorMessage = ''; // Clear error if successfully saved
      }, error => {
        console.error('Error saving user', error);
        if (error.status === 400 && error.error) {
          // Assuming backend sends validation errors as a JSON object
          this.errorMessage = Object.values(error.error).join(' | ');
        } else {
          this.errorMessage = 'An unexpected error occurred. Please try again.';
        }
      });
  }

  navigateToLogin(){
    this.router.navigate(['/login']);  
  }



  calculateAge() {
    if (this.user.dob) {
      const birthDate = new Date(this.user.dob);
      const today = new Date();
      
      // Calculate age
      let age = today.getFullYear() - birthDate.getFullYear();
      
      // Adjust for month/day not yet passed in the current year
      const monthDiff = today.getMonth() - birthDate.getMonth();
      if (
        monthDiff < 0 || 
        (monthDiff === 0 && today.getDate() < birthDate.getDate())
      ) {
        age--;
      }
  
      this.user.age = age.toString(); // Update the age field
    }
  }

}
