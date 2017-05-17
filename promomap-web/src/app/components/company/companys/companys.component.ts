import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../../../services/company-rest.service';
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

	private titles = ["Nome", "Descrição", "CNPJ", "Ações"];
  constructor(private companyService: CompanyService) { }

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

}
