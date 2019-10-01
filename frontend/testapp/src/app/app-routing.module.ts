import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component';
//import { UserFormComponent } from './user-form/user-form.component';
import { AltapolizaComponent } from './altapoliza/altapoliza.component';
import { ConsultarpolizaComponent } from './consultarpoliza/consultarpoliza.component';
 
const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'altapoliza', component: AltapolizaComponent },
  { path: 'consultarpoliza', component: ConsultarpolizaComponent }//,
  //{ path: 'adduser', component: UserFormComponent }
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
