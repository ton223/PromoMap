import { Component, Inject, OnInit } from '@angular/core';
import { FileUploader } from 'ng2-file-upload';
import { ImageService } from '../../../services/image-rest.service';
import { DOCUMENT } from '@angular/platform-browser';

import { CompanyService } from '../../../services/company-rest.service';
import { Product } from '../../../models/Product'
import { productDAO } from '../../../dao/product-dao';

const URL = '';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css'],
  providers: [ImageService, CompanyService]
})
export class CreateProductComponent implements OnInit {

	private error: boolean;
  private errorMessage: string;
  private imageURL: string;
  private registering: boolean;

  private uploader:FileUploader = new FileUploader({url: URL});

  private product = new Product();

  constructor(private companyService: CompanyService, 
  private imageService: ImageService, 
  @Inject(DOCUMENT) private document: any) { }

  ngOnInit() {
    this.registering = false;
  	this.imageURL = this.imageService.getDefaultImageURL();
  }

  public registerProduct(event: any) {
    this.registering = true;
    this.uploadImageAndRegisterProduct(event);
  }

  public _register(event: any) {
    let parts = event.toElement.formAction.split('/');
    let last = parts.length-1;
    let companyId = parts[last];
    
    this.companyService.registerProduct(companyId, this.product).then(
      response => {
        if (response.status === 200) {
          const task = response.entity;
          if (task.success) {
            productDAO.add(task.data);
            this.product = new Product();
            this.resetImage();
            this.close();
          } else {
            this.error = true;
            this.errorMessage = task.errorMessage;
            this.registering = false;
          }
        } else {
          this.error = true;
          this.errorMessage = response.statusInfo;
          this.registering = false;
        }
      },
      error => {
        this.errorMessage = <any>error;
        this.error = true;
        this.registering = false;
      }
    );
  }

  private resetImage() {
    this.uploader = new FileUploader({url: URL});
    this.imageURL = this.imageService.getDefaultImageURL();
  }

  public openFileSelect() {
    if(!this.registering) {
      this.document.getElementById('fileSelect').click();
    }
  }

  public onFileChange(event: any) {
    if(this.uploader.queue.length > 1) {
      let file = this.uploader.queue[1];
      this.uploader = new FileUploader({url: URL});
      this.uploader.queue.push(file);
    }
    if(this.uploader.queue.length > 0) {
      let parts = this.uploader.queue[0].file.type.split('/');
      if(parts[0] != 'image') {
        this.resetImage();
      } else {
        this.pickImageURL(event);
      }
    }
  }

  private uploadImageAndRegisterProduct(event: any) {
    if(this.uploader.queue.length == 0) {
      this.registering = false;
      this._register(event);
      return;
    }
    this.imageService.uploadImages(this.uploader).then(
      response => {
        this.product.setImagePublicId(response.public_id);
        this.registering = false;
        this._register(event);
      },
      error => {
        this.errorMessage = <any>error;
        this.error = true;
      }
    );
  }

  private pickImageURL(event: any): void {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();
      reader.onload = (event) => {
        let target: any;
        target = event.target;
        this.imageURL = target.result;
      }
      reader.readAsDataURL(event.target.files[0]);
    }
  }

  public close() {
    this.document.getElementById('closeButton').click();
  }

}
