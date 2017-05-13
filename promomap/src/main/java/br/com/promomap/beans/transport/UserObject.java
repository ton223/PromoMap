package br.com.promomap.beans.transport;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */

public class UserObject {
	
	private String superId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private Date createdAt;
	
	private Set<CompanyObject> companys;

	public UserObject() {
		companys = new HashSet<CompanyObject>();
	}

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

	public UserObject setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserObject setEmail(String email) {
		this.email = email;
		return this;
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

	public Set<CompanyObject> getCompanys() {
		return companys;
	}

	public void setCompanys(Set<CompanyObject> companys) {
		this.companys = companys;
	}

	@Override
	public String toString() {
		return "UserObject [superId=" + superId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + ", createdAt=" + createdAt + "]";
	}
	
}
