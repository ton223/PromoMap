import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';

import { Cloudinary } from 'cloudinary-core';
import { CloudinaryModule } from '@cloudinary/angular';
import { FileUploadModule } from "ng2-file-upload";

import { AgmCoreModule } from 'angular2-google-maps/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { CreateCompanyComponent } from './components/company/create-company.component';
import { MapComponent } from './components/map/map.component';
import { MapMarkerComponent } from './components/map/map-marker.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { CompanysComponent } from './components/company/companys/companys.component';
import { ProductsComponent } from './components/company/products/products.component';
import { CompanyAdminComponent } from './components/company/company-admin/company-admin.component';
import { CreateProductComponent } from './components/company/create-product/create-product.component';

const appRoutes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'create-company', component:  CreateCompanyComponent},
  { path: 'map-marker', component:  MapMarkerComponent},
  { path: 'profile', component:  ProfileComponent},
  { path: 'company-admin/:id', component:  CompanyAdminComponent}
];

export const cloudinaryLib = {
  Cloudinary: Cloudinary
};

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    SidebarComponent,
    CreateCompanyComponent,
    MapComponent,
    MapMarkerComponent,
    ProfileComponent,
    CompanysComponent,
    ProductsComponent,
    CompanyAdminComponent,
    CreateProductComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBqo98hmF-8oiavOEWtd_MNSb6QV3kXcmM',
      libraries: ['places']
    }),
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    CloudinaryModule.forRoot(cloudinaryLib, { cloud_name: 'luk40cloud'}),
    FileUploadModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

