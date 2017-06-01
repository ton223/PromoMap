import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Company } from '../../../models/Company';
import { Marker } from '../../../models/Marker';
import { CompanyService } from '../../../services/rest/company-rest.service';

@Component({
  selector: 'app-company-admin',
  templateUrl: './company-admin.component.html',
  styleUrls: ['./company-admin.component.css'],
  providers: [CompanyService]
})
export class CompanyAdminComponent implements OnInit, OnDestroy {

  private error: boolean;
  private errorMessage: string;
  private isEditing: boolean;

  private sub: any;
  private company = new Company();
  private companyEdited: Company;

  constructor(private route: ActivatedRoute, private companyService: CompanyService) {}

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      let companyId = params['id'];
      this.companyService.findBySuperId(companyId).then(
      response => {
        if (response.status === 200) {
          const task = response.entity;
          if (task.success) {
            this.company = Object.assign(new Company(), task.data);
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
        this.errorMessage = <any>error;
        this.error = true;
      }
    );
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  public editCompany() {
    if(this.company.equals(this.companyEdited)){
      this.isEditing = false;
      return;
    }
    this.companyService.edit(this.company.getSuperId(), this.companyEdited).then(
      response => {
        if (response.status === 200) {
          const task = response.entity;
          if (task.success) {
            this.company = Object.assign(new Company(), this.companyEdited);
            this.isEditing = false;
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
        this.errorMessage = <any>error;
        this.error = true;
      }
    );
  }

  public cancelEdit() {
  	this.isEditing = false;
    this.error = false;
  }

  public setEditing() {
  	this.isEditing = true;
  	this.companyEdited = Object.assign(new Company(), this.company);
  }

}
