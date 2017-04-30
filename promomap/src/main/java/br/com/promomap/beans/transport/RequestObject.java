package br.com.promomap.beans.transport;

public class RequestObject {
	private String token;
	
	private Object data;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RequestObject [token=" + token + ", data=" + data + "]";
	}
	
}
