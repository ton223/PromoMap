import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Company } from '../../models/Company';
import { MapMarkerComponent } from '../map/map-marker.component';
import { CompanyService } from '../../services/company-rest.service';

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css'],
  providers: [CompanyService]
})
export class CreateCompanyComponent {
  private error: boolean;
  private errorMessage: string;
  private created: boolean;
  private mapVisible: boolean;
  private terms: boolean;
  //
  private company = new Company();
  private coords: string;

  constructor(private companyService: CompanyService, private router: Router) { }

  public create(): void {
    this.companyService.create(this.company).then(
      response => {
        if (response.status === 200) {
          const task = response.entity;
          if (task.success) {
            this.created = true;
            this.router.navigate(['/company-admin', task.data.superId])
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

  private updateLocation(): void {
    const marker = MapMarkerComponent.getMarker();
    this.company.setLocation(marker);
    this.coords = marker.getLat() + ', ' + marker.getLng();
  }

}
