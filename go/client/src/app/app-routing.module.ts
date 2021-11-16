import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { BlogComponent } from './blog/blog.component';
import { HakkindaComponent } from './hakkinda/hakkinda.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import {DetailComponent} from "./blog/detail/detail.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'auth', component: AuthComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'hakkinda', component: HakkindaComponent },
  { path: '**', component:DetailComponent, pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
