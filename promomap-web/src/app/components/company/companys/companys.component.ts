import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../../../services/rest/company-rest.service';
import { Router } from '@angular/router';
import { Company } from '../../../models/Company';

@Component({
  selector: 'app-companys',
  templateUrl: './companys.component.html',
  styleUrls: ['./companys.component.css'],
  providers: [CompanyService]
})
export class CompanysComponent implements OnInit {
	private error: boolean;
	private errorMessage: string;
	private companys: Company[];

  constructor(private companyService: CompanyService, private router: Router) { }

  ngOnInit() {
  	this.fetchCompanys();
  }

  public fetchCompanys() {
  	this.companyService.list().then(
      response => {
        if (response.status === 200) {
          const task = response.entity;
          if (task.success) {
            this.companys = task.dataList;
          } else {
            this.error = true;
            this.errorMessage = task.errorMessage;
          }
        } else {
          this.error = true;
          this.errorMessage = response.statusInfo;
        }
      },
      error => this.errorMessage = <any>error
    );
  }

  public deleteCompany(company: any){
  	this.companyService.delete(company.superId).then(
      response => {
        if (response.status === 200) {
          const task = response.entity;
          if (task.success) {
          	let index = this.companys.indexOf(company);
            this.companys.splice(index, 1);
          } else {
            this.error = true;
            this.errorMessage = task.errorMessage;
          }
        } else {
          this.error = true;
          this.errorMessage = response.statusInfo;
        }
      },
      error => {
      	this.error = true;
      	this.errorMessage = <any>error;
      }
    );
  }

  public companyAdmin(company: any) {
  	this.router.navigate(['company-admin', company.superId]);
  }

}
