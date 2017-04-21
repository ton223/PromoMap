import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { Test } from './components/test/test.component';
import { Register } from './components/register/register.component';

const appRoutes: Routes = [
  { path: 'test', component: Test },
  { path: 'register', component: Register }
];

@NgModule({
  declarations: [
    AppComponent,
    Register,
    Test
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
