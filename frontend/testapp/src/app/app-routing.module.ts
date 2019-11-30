import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AltapolizaComponent } from './altapoliza/altapoliza.component';
import { ConsultarpolizaComponent } from './consultarpoliza/consultarpoliza.component';
import { BuscarclienteComponent } from './buscarcliente/buscarcliente.component';
import { AltaclienteComponent } from './altacliente/altacliente.component';
import { IndexComponent } from './index/index.component';
import { TipoCobertura } from 'TipoCobertura';
import { TipocoberturaComponent } from './tipocobertura/tipocobertura.component';
import { BuscarPolizaComponent } from './buscar-poliza/buscar-poliza.component';
import { RegistrarPagoComponent } from './registrar-pago/registrar-pago.component';
import { ConsultarclientesComponent } from './consultarclientes/consultarclientes.component';
import { ActualizarFactoresComponent } from './actualizar-factores/actualizar-factores.component';
 
const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'alta-poliza', component: AltapolizaComponent },
  { path: 'consultar-poliza', component: ConsultarpolizaComponent },
  { path: 'buscar-cliente', component: BuscarclienteComponent },
  { path: 'alta-cliente', component: AltaclienteComponent },
  { path: 'buscar-poliza', component: BuscarPolizaComponent },
  { path: 'registrar-pago', component: RegistrarPagoComponent },
  { path: 'consultar-clientes', component: ConsultarclientesComponent },
  { path: 'actualizar-factores', component: ActualizarFactoresComponent },
  { path: '', component: IndexComponent }
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
