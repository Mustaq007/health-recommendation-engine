import { Routes } from '@angular/router';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { DoctorDashboardComponent } from './doctor-dashboard/doctor-dashboard.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { LoginComponent } from './login/login.component';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirect to login page
     { path: 'login', component: LoginComponent },
    { path: 'admin-dashboard', component: AdminDashboardComponent },
    { path: 'doctor-dashboard', component: DoctorDashboardComponent },
    { path: 'user-dashboard', component: UserDashboardComponent },
    { path: '**', component: LoginComponent } // Wildcard route for 404
  ];