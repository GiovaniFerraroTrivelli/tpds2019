import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UserListComponent } from './user-list/user-list.component';
import { AltapolizaComponent } from './altapoliza/altapoliza.component';
import { ConsultarpolizaComponent } from './consultarpoliza/consultarpoliza.component';
import { BuscarclienteComponent } from './buscarcliente/buscarcliente.component';
import { AltaclienteComponent } from './altacliente/altacliente.component';
import { IndexComponent } from './index/index.component';
import { TipoCobertura } from 'TipoCobertura';
import { TipocoberturaComponent } from './tipocobertura/tipocobertura.component';
 
const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'users', component: UserListComponent },
  { path: 'alta-poliza', component: AltapolizaComponent },
  { path: 'consultar-poliza', component: ConsultarpolizaComponent },
  { path: 'buscar-cliente', component: BuscarclienteComponent },
  { path: 'alta-cliente', component: AltaclienteComponent },
  { path: '', component: IndexComponent },
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
