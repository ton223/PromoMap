package br.com.promomap.beans.transport;

import java.util.Date;

public class UserObject {
	
	private String superId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private Date createdAt;

	public String getSuperId() {
		return superId;
	}

	public UserObject setSuperId(String superId) {
		this.superId = superId;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public UserObject setFirstName(String name) {
		this.firstName = name;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "UserObject [superId=" + superId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + ", createdAt=" + createdAt + "]";
	}
	
}
