import { BrowserModule, Title } from '@angular/platform-browser';
import { LOCALE_ID, NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { registerLocaleData } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from './app.component';

import { DialogService } from './dialog/dialog.service';
import { GeografiaService } from './geografia/geografia.service';
import { ModelosService } from './modelos/modelos.service';
import { AltaPolizaService } from './altapoliza/altapoliza.service';
import { BusquedaClienteService } from './buscarcliente/busquedacliente.service';
import { ConsultarclientesService } from './consultarclientes/consultarclientes.service';
import { LoadingService } from './loading/loading.service';

import { AltapolizaComponent } from './altapoliza/altapoliza.component';
import { ConsultarpolizaComponent } from './consultarpoliza/consultarpoliza.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { DialogComponent } from './dialog/dialog.component';
import { IndexComponent } from './index/index.component';
import { BuscarclienteComponent } from './buscarcliente/buscarcliente.component';
import { AltaclienteComponent } from './altacliente/altacliente.component';
import { HijosComponent } from './hijos/hijos.component';
import { TipocoberturaComponent } from './tipocobertura/tipocobertura.component';
import { LoadingComponent } from './loading/loading.component';
import { ConsultarclientesComponent } from './consultarclientes/consultarclientes.component';
import { BuscarPolizaComponent } from './buscar-poliza/buscar-poliza.component';
import { ActualizarFactoresComponent } from './actualizar-factores/actualizar-factores.component';
import { GenerarInformeMensualComponent } from './generar-informe-mensual/generar-informe-mensual.component';
import { RegistrarPagoPolizaComponent } from './registrar-pago-poliza/registrar-pago-poliza.component';

import localeEsAr from '@angular/common/locales/es-AR';
import { ErrorComponent } from './error/error.component';

registerLocaleData(localeEsAr, 'es-AR');

@NgModule({
	declarations: [
		AppComponent,
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
		ConsultarclientesComponent,
		ActualizarFactoresComponent,
		GenerarInformeMensualComponent,
		RegistrarPagoPolizaComponent,
		ErrorComponent
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
		DialogService,
		Title,
		GeografiaService,
		ModelosService,
		AltaPolizaService,
		BusquedaClienteService,
		ConsultarclientesService,
		LoadingService
	],
	
	entryComponents: [ DialogComponent ],
	bootstrap: [ AppComponent ]
})

export class AppModule { }
