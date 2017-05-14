package br.com.promomap.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.promomap.beans.persistence.Session;
import br.com.promomap.beans.persistence.User;
import br.com.promomap.beans.transport.TaskObject;
import br.com.promomap.beans.transport.UserObject;
import br.com.promomap.dao.UserDAO;
import br.com.promomap.model.enums.GenderEnum;
import br.com.promomap.utils.Utils;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */

@Service
public class UserService {

	@Autowired
	public UserDAO userDAO;
	
	@Autowired
	private SessionService sessionService;

	public List<UserObject> listAll() {
		List<UserObject> usersO = new ArrayList<>();
		List<User> users = userDAO.listAll();
		users.forEach(u -> usersO.add(u.generateTransportObject()));
		return usersO;
	}
	
	public TaskObject login(UserObject userO) {
		TaskObject task = new TaskObject();
		User user = this.userDAO.findByEmailAndPassword(userO.getEmail(), userO.getPassword());
		if(user != null) {
			task.setSuccess(true);
			
			String token = Utils.generateRandomUUID();
			Session session = new Session();
			session.setLoginDate(new Date());
			session.setToken(token);
			session.setUser(user);
			session.setLogged(true);
			sessionService.create(session);
			task.setData(user.generateTransportObject());
			task.setInfo(token);
		} else {
			task.setErrorMessage("E-mail e/ou senha inválido(s).");
			task.setSuccess(false);
		}
		return task;
	}

	public TaskObject create(UserObject userO) {
		TaskObject task = new TaskObject();
		if(userO.getEmail() == null || userO.getEmail().isEmpty() || this.isEmailInUse(userO.getEmail())) {
			task.setErrorMessage("Este e-mail já está em uso.");
			task.setSuccess(false);
			return task;
		}
		try {
			User user = new User();
			String superId = Utils.generateRandomUUID();
			user.setSuperId(superId);
			user.setFirstName(userO.getFirstName());
			user.setLastName(userO.getLastName());
			user.setEmail(userO.getEmail());
			user.setPassword(userO.getPassword());
			user.setCreatedAt(new Date());
			userDAO.save(user);
			
			User createdUser = this.findBySuperId(superId);
			task.setData(createdUser.generateTransportObject());
			task.setSuccess(true);
		} catch (Exception e) {
			task.setErrorMessage(e.getMessage());
			task.setSuccess(false);
		}
		return task;
	}
	
	public TaskObject logout(String token) {
		TaskObject task = new TaskObject();
		task.setSuccess(true);
		this.sessionService.closeSession(token);
		return task;
	}
	
	public TaskObject edit(User user, UserObject userEdited) {
		TaskObject task = new TaskObject();
		if(!user.getSuperId().equals(userEdited.getSuperId())) {
			task.setSuccess(false);
			task.setErrorMessage("Você não pode editar informações de outro usuário.");
		}
		try {
			user.setFirstName(userEdited.getFirstName());
			user.setLastName(userEdited.getLastName());
			if(userEdited.getPassword() != null && !userEdited.getPassword().isEmpty()) {
				user.setPassword(userEdited.getPassword());
			}
			user.setEmail(userEdited.getEmail());
			user.setGender(GenderEnum.fromDescription(userEdited.getGender()));
			user.setPhone(userEdited.getPhone());
			
			userDAO.save(user);
		} catch (Exception e) {
			task.setSuccess(false);
			task.setErrorMessage(e.getMessage());
		}
		task.setSuccess(true);
		return task;
	}
	
	public User findBySuperId(String superId) {
		return this.userDAO.findBySuperId(superId);
	}
	
	public boolean isEmailInUse(String email) {
		User userByEmail = this.userDAO.findByEmail(email);
		if(userByEmail == null) {
			return false;
		} else {
			return true;
		}
	}

	public void delete(UserObject userO) {
		User user = userDAO.findBySuperId(userO.getSuperId());
		user.setDeleted(true);
		user.setDeletedDate(new Date());
		userDAO.save(user);
	}
	
	public User findUserBySessionToken(String token) {
		Session session = this.sessionService.findByToken(token);
		return session.getUser();
	}

}
