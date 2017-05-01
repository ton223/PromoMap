import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Company } from '../../models/Company';

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent {
  private error: boolean;
  private company = new Company();

  public create(): void {

  }
}
