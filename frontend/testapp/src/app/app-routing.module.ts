import { NgModule } from '@angular/core';
import { Routes, RouterModule, CanActivate } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AltapolizaComponent } from './altapoliza/altapoliza.component';
import { ConsultarpolizaComponent } from './consultarpoliza/consultarpoliza.component';
import { BuscarclienteComponent } from './buscarcliente/buscarcliente.component';
import { AltaclienteComponent } from './altacliente/altacliente.component';
import { IndexComponent } from './index/index.component';
import { ErrorComponent } from './error/error.component';
import { BuscarPolizaComponent } from './buscar-poliza/buscar-poliza.component';
import { ConsultarclientesComponent } from './consultarclientes/consultarclientes.component';
import { ActualizarFactoresComponent } from './actualizar-factores/actualizar-factores.component';
import { GenerarInformeMensualComponent } from './generar-informe-mensual/generar-informe-mensual.component';
import { RegistrarPagoPolizaComponent } from './registrar-pago-poliza/registrar-pago-poliza.component';
import { AuthenticationService } from './authentication/authentication.service';

const routes: Routes = [
	{ path: 'login', component: LoginComponent },
	{ path: 'alta-poliza', component: AltapolizaComponent, canActivate: [ AuthenticationService ] },
	{ path: 'consultar-poliza', component: ConsultarpolizaComponent, canActivate: [ AuthenticationService ] },
	{ path: 'buscar-cliente', component: BuscarclienteComponent, canActivate: [ AuthenticationService ] },
	{ path: 'alta-cliente', component: AltaclienteComponent, canActivate: [ AuthenticationService ] },
	{ path: 'buscar-poliza', component: BuscarPolizaComponent, canActivate: [ AuthenticationService ] },
	{ path: 'consultar-clientes', component: ConsultarclientesComponent, canActivate: [ AuthenticationService ] },
	{ path: 'actualizar-factores', component: ActualizarFactoresComponent, canActivate: [ AuthenticationService ] },
	{ path: 'generar-informe-mensual', component: GenerarInformeMensualComponent, canActivate: [ AuthenticationService ] },
	{ path: 'registrar-pago-poliza', component: RegistrarPagoPolizaComponent, canActivate: [ AuthenticationService ] },
	{ path: 'error', component: ErrorComponent },
	{ path: '', component: IndexComponent },
	{ path: '**', redirectTo: '/' }
];
 
@NgModule({
	imports: [ RouterModule.forRoot(routes) ],
	exports: [ RouterModule ]
})

export class AppRoutingModule { }
