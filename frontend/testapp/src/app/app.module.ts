import { BrowserModule, Title } from '@angular/platform-browser';
import { LOCALE_ID, NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { registerLocaleData } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from './app.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserService } from './user-service.service';
import { AltapolizaComponent } from './altapoliza/altapoliza.component';
import { ConsultarpolizaComponent } from './consultarpoliza/consultarpoliza.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { DialogComponent } from './dialog/dialog.component';
import { DialogService } from './dialog/dialog.service';
import { IndexComponent } from './index/index.component';
import { BuscarclienteComponent } from './buscarcliente/buscarcliente.component';
import { AltaclienteComponent } from './altacliente/altacliente.component';
import { HijosComponent } from './hijos/hijos.component';
import { TipocoberturaComponent } from './tipocobertura/tipocobertura.component';
import { GeografiaService } from './geografia/geografia.service';
import { ModelosService } from './modelos/modelos.service';
import { AltaPolizaService } from './altapoliza/altapoliza.service';
import { BusquedaClienteService } from './buscarcliente/busquedacliente.service';
import { LoadingService } from './loading/loading.service';
import { LoadingComponent } from './loading/loading.component';

import localeEsAr from '@angular/common/locales/es-AR';
import { BuscarPolizaComponent } from './buscar-poliza/buscar-poliza.component';
import { RegistrarPagoComponent } from './registrar-pago/registrar-pago.component';

registerLocaleData(localeEsAr, 'es-AR');

@NgModule({
	declarations: [
		AppComponent,
		UserListComponent,
		AltapolizaComponent,
		ConsultarpolizaComponent,
		LoginComponent,
		HeaderComponent,
		FooterComponent,
		DialogComponent,
		IndexComponent,
		BuscarclienteComponent,
		AltaclienteComponent,
		HijosComponent,
		TipocoberturaComponent,
		LoadingComponent,
		BuscarPolizaComponent,
		RegistrarPagoComponent
	],

	imports: [
		BrowserModule,
		AppRoutingModule,
		NgbModule,
		HttpClientModule,
		FormsModule,
		ReactiveFormsModule
	],

	providers: [
		{ provide: LOCALE_ID, useValue: 'es-AR' },
		UserService,
		DialogService,
		Title,
		GeografiaService,
		ModelosService,
		AltaPolizaService,
		BusquedaClienteService,
		LoadingService
	],
	
	entryComponents: [ DialogComponent ],
	bootstrap: [ AppComponent ]
})

export class AppModule { }
