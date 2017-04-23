export class SessionDAO {
	private static token: string;

	public static hasToken(): boolean {
		if(this.token.length > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static setToken(token: string) {
		this.token = token;
	}

	public static getToken(): string {
		return this.token;
	}
}