import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyAdminComponent } from './company-admin.component';

describe('CompanyAdminComponent', () => {
  let component: CompanyAdminComponent;
  let fixture: ComponentFixture<CompanyAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompanyAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompanyAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
