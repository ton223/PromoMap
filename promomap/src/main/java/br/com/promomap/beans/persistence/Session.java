package br.com.promomap.beans.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.promomap.beans.transport.SessionObject;
import br.com.promomap.model.TransportObjectInterface;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */

@Entity
@Table(name = "Session")
public class Session implements TransportObjectInterface<SessionObject> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "token")
	private String token;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "logged", columnDefinition = "tinyint DEFAULT 0")
	private boolean logged;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "loginDate")
	private Date loginDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "logoutDate")
	private Date logoutDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getLogoutDate() {
		return logoutDate;
	}

	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	@Override
	public SessionObject generateTransportObject() {
		SessionObject sessionO = new SessionObject();
		sessionO.setToken(getToken());
		sessionO.setLogged(isLogged());
		sessionO.setLoginDate(getLoginDate());
		sessionO.setLogoutDate(getLogoutDate());
		sessionO.setUser(getUser().generateTransportObject());
		return sessionO;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", token=" + token + ", user=" + user + ", logged=" + logged + ", loginDate="
				+ loginDate + ", logoutDate=" + logoutDate + "]";
	}

}
