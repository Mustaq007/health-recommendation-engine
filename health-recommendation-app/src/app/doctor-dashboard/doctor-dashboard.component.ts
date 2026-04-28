import { CommonModule, NgIf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
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
  lifeStyleHabit:string;
  requireConsultation:boolean
}

@Component({
  selector: 'app-doctor-dashboard',
  standalone: true,
  imports: [CommonModule,NgIf, FormsModule],
  templateUrl: './doctor-dashboard.component.html',
  styleUrl: './doctor-dashboard.component.scss'
})
export class DoctorDashboardComponent {

   userData: UserDetails | null = null;
    email: string = '';
    errorMessage: string = '';
    successMessage:string='';
    users: UserDetails[] = [];
    showUserList:boolean=false;
    showNoData:boolean=false;
    hiddenUserDetailsCard:boolean = true;
    
  
    constructor(private http: HttpClient,private router: Router) {}
  
    ngOnInit(): void {
      this.fetchUsersList();
    }

    fetchUsersList() {
      this.http.get<UserDetails[]>(`http://localhost:8000/api/users/details`)
        .subscribe({
          next: (data) => {
            this.users = data; 
            if(this.users.length>0){
              this.showUserList = true;
            }else{
              this.showNoData = true;
            }
            this.errorMessage = '';
          },
          error: () => {
            this.errorMessage = 'User not found or error fetching data';
            this.userData = null;
          }
        });
    }

    updateUserData() {
      this.errorMessage = ''; // Reset error message
      if(this.userData){
        this.userData.requireConsultation=false;
      }
      this.http.patch('http://localhost:8000/api/users/update', this.userData)
        .subscribe(response => {
          console.log('User saved successfully', response);
          // this.isSubmitted = true;
          this.errorMessage = ''; // Clear error if successfully saved
          this.successMessage='Prescription Added Successfully'
        }, error => {
          console.error('Error saving user', error);
          this.errorMessage = error.error;
        });
    }

    navigateToLogin(){
      this.router.navigate(['/login']);  

    }


    fetchUser(emailId:any) {
      this.http.get<UserDetails>(`http://localhost:8000/api/users/details/${emailId}`)
        .subscribe({
          next: (data) => {
            this.userData = data;
            this.errorMessage = '';
            this.showUserList = false;
            this.hiddenUserDetailsCard = false
          },
          error: () => {
            this.errorMessage = 'User not found or error fetching data';
            this.userData = null;
          }
        });
    }
    navigateToBack(){
      this.hiddenUserDetailsCard = true;
      this.fetchUsersList();

    }




}
