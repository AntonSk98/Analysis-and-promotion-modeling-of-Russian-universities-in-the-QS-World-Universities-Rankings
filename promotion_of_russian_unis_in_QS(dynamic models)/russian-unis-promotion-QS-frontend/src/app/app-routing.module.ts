import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {MainPageComponent} from './main-page/main-page.component';
import {UniversityGeneralInformationComponent} from './university-general-information/university-general-information.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {DynamicModelingComponent} from './dynamic-modeling/dynamic-modeling.component';



const routes: Routes = [
  { path: '',   redirectTo: '/main-page', pathMatch: 'full' },
  { path: 'main-page', component: MainPageComponent, pathMatch: 'full'},
  { path: 'university-general-information/:id/:university', component: UniversityGeneralInformationComponent},
  { path: 'university-modeling/:id/:university/:modelingType', component: DynamicModelingComponent},
  { path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
