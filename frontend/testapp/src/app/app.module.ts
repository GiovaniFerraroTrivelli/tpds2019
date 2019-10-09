import { BrowserModule, Title } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
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
import { BuscarpolizaComponent } from './buscarpoliza/buscarpoliza.component';
import { AltaclienteComponent } from './altacliente/altacliente.component';
import { HijosComponent } from './hijos/hijos.component';
import { TipocoberturaComponent } from './tipocobertura/tipocobertura.component';

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
		BuscarpolizaComponent,
		AltaclienteComponent,
		HijosComponent,
		TipocoberturaComponent
	],

	imports: [
		BrowserModule,
		AppRoutingModule,
		NgbModule,
		HttpClientModule,
		FormsModule
	],

	providers: [
		UserService,
		DialogService,
		Title
	],
	
	entryComponents: [ DialogComponent ],
	bootstrap: [ AppComponent ]
})

export class AppModule { }
