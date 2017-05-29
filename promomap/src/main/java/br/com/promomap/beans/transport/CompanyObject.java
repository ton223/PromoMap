/*
 * @(#)CompanyObject.java 10 de mai de 2017 - 22:24:08
 *
 */
package br.com.promomap.beans.transport;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
public class CompanyObject {
	private String superId;

	private String name;

	private String description;

	private String cnpj;

	private String phone;

	private String email;

	private LocationObject location;

	private UserObject user;

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocationObject getLocation() {
		return location;
	}

	public void setLocation(LocationObject location) {
		this.location = location;
	}

	public UserObject getUser() {
		return user;
	}

	public void setUser(UserObject user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((superId == null) ? 0 : superId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyObject other = (CompanyObject) obj;
		if (superId == null) {
			if (other.superId != null)
				return false;
		} else if (!superId.equals(other.superId))
			return false;
		return true;
	}

}
