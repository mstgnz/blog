import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HakkindaComponent } from './hakkinda/hakkinda.component';

const routes: Routes = [
  {
    path: 'hakkinda',
    component: HakkindaComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
