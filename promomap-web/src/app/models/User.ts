
export class User {
	private firstName:string;
	private lastName:string;
	private password:string;
	private email:string;

	public getfirstName(): any {
		return this.firstName;
	}

	public setfirstName(firstName: string): void {
		this.firstName = firstName;
	}

	public getlastName(): any {
		return this.lastName;
	}

	public setlastName(lastName: string): void {
		this.lastName = lastName;
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