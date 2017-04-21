package br.com.promomap.beans.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.promomap.beans.transport.UserObject;
import br.com.promomap.model.PersistenceBeanInterface;

@Entity
@Table(name="User")
public class User implements Serializable, PersistenceBeanInterface<UserObject>{
	
	private static final long serialVersionUID = 5306900357808593432L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="superId")
	private String superId;

	@Column(name="name")
	private String name;
	
	@Column(name="deleted", columnDefinition = "tinyint DEFAULT 0")
	private boolean deleted;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="deletedDate")
	private Date deletedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	@Override
	public String toString() {
		return "User [id=" + id + ", superId=" + superId + ", name=" + name + ", deleted=" + deleted + ", deletedDate="
				+ deletedDate + "]";
	}

	@Override
	public UserObject generateTransportObject() {
		UserObject userObject = new UserObject();
		userObject.setSuperId(getSuperId());
		userObject.setName(getName());
		return userObject;
	}

}
