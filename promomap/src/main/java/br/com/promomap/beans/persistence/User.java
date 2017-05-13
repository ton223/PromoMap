package br.com.promomap.beans.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.promomap.beans.transport.UserObject;
import br.com.promomap.model.PersistenceBeanInterface;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */

@Entity
@Table(name = "User")
public class User implements PersistenceBeanInterface<UserObject> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "superId", nullable = false, unique = true)
	private String superId;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt")
	private Date createdAt;

	@Column(name = "deleted", columnDefinition = "tinyint DEFAULT 0")
	private boolean deleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deletedDate")
	private Date deletedDate;
	
	@OneToMany
	private Set<Company> companys;

	
	public User() {
		companys = new HashSet<Company>();
	}

	public Long getId() {
		return id;
	}

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Set<Company> getCompanys() {
		return companys;
	}

	public void setCompanys(Set<Company> companys) {
		this.companys = companys;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", superId=" + superId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", createdAt=" + createdAt + ", deleted=" + deleted
				+ ", deletedDate=" + deletedDate + "]";
	}

	@Override
	public UserObject generateTransportObject() {
		UserObject userObject = new UserObject();
		userObject.setSuperId(getSuperId());
		userObject.setFirstName(getFirstName());
		userObject.setLastName(getLastName());
		userObject.setEmail(getEmail());
//		userObject.setPassword(getPassword());
		userObject.setCreatedAt(getCreatedAt());
		for(Company company : getCompanys()) {
			userObject.getCompanys().add(company.generateTransportObject());
		}
		
		return userObject;
	}

}
