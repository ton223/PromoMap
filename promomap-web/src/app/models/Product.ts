import { Company } from './Company';

export class Product {
    private superId: string;
	private imagePublicId: string;
	private code: string;
	private name: string;
	private description: string;
	private price: number;
	private rating: number;
	private company: Company;

    public getSuperId() {
		return this.superId;
	}

	public setSuperId(superId: string) {
		this.superId = superId;
	}

	public getImagePublicId(): string {
		return this.imagePublicId;
	}

	public setImagePublicId(imagePublicId: string) {
		this.imagePublicId = imagePublicId;
	}

	public getCode(): string {
		return this.code;
	}

	public setCode(code: string) {
		this.code = code;
	}

	public getName(): string {
		return this.name;
	}

	public setName(name: string) {
		this.name = name;
	}

	public getDescription(): string {
		return this.description;
	}

	public setDescription(description: string) {
		this.description = description;
	}

	public getPrice(): number {
		return this.price;
	}

	public setPrice(price: number) {
		this.price = price;
	}

	public getRating(): number {
		return this.rating;
	}

	public setRating(rating: number) {
		this.rating = rating;
	}

	public getCompany(): Company {
		return this.company;
	}

	public setCompany(company: Company) {
		this.company = company;
	}
}