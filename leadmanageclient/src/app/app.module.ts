import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/LoginRegistration/login/login.component';
import { RegisterComponent } from './components/LoginRegistration/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { HttpInterceptorService } from './services/HttpInterceptorService';
import { CaptureformComponent } from './components/leadForms/captureform/captureform.component';
import { ContactformComponent } from './components/leadForms/contactform/contactform.component';
import { ContacttableComponent } from './components/pages/contacttable/contacttable.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LeaddetailsComponent } from './components/pages/leaddetails/leaddetails.component';
import { LeadformpageComponent } from './components/pages/leadformpage/leadformpage.component';
import { PipelineComponent } from './components/pages/pipeline/pipeline.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CustomerComponent } from './components/pages/customer/customer.component';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { ReportComponent } from './components/pages/report/report.component';
import { NgChartsModule } from 'ng2-charts';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    CaptureformComponent,
    ContactformComponent,
    ContacttableComponent,
    NavbarComponent,
    LeaddetailsComponent,
    LeadformpageComponent,
    PipelineComponent,
    CustomerComponent,
    DashboardComponent,
    ReportComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    NgChartsModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
