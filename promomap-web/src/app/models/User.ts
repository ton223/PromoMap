
export class User {
	private name:string;
	private password:string;
	private email:string;

	public getName(): any {
		return this.name;
	}

	public setName(name: string): void {
		this.name = name;
	}

	public setPassword(password: string): void {
		this.password = password;
	}

	public getPassword(): string {
		return this.password;
	}

	public setEmail(email: string): void {
		this.email = email;
	}

	public getEmail(): any {
		return this.email;
	}
}