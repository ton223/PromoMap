package br.com.promomap.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.promomap.beans.persistence.User;
import br.com.promomap.beans.transport.UserObject;
import br.com.promomap.dao.UserDAO;
import br.com.promomap.utils.Utils;

@Service
public class UserService {
	
	@Autowired
	public UserDAO userDAO;
	
	public List<UserObject> listAll(){
		List<UserObject> usersO = new ArrayList<>();
		List<User> users = userDAO.listAll();
		users.forEach(u -> usersO.add(u.generateTransportObject()));
		return usersO;
	}
	
	public UserObject create(UserObject userO) {
		User user = new User();
		String superId = Utils.generateRandomUUID();
		user.setSuperId(superId);
		user.setName(userO.getName());
		userDAO.save(user);
		
		userO.setSuperId(superId);
		return userO;
	}
	
	public void delete(UserObject userO) {
		User user = userDAO.findBySuperId(userO.getSuperId());
		user.setDeleted(true);
		user.setDeletedDate(new Date());
		userDAO.save(user);
	}
	
}
