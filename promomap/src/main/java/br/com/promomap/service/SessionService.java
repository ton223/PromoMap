package br.com.promomap.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.promomap.beans.persistence.Session;
import br.com.promomap.dao.SessionDAO;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */

@Service
public class SessionService {

	@Autowired
	private SessionDAO sessionDAO;
	
	public void create(Session session) {
		this.sessionDAO.save(session);
	}
	
	public Session findByToken(String token) {
		return this.sessionDAO.getByToken(token);
	}
	
	public void closeSession(String token) {
		Session session = this.findByToken(token);
		if(session != null) {
			session.setLogged(false);
			session.setLogoutDate(new Date());
			this.update(session);			
		}
	}
	
	public void update(Session session) {
		this.sessionDAO.save(session);
	}
}
