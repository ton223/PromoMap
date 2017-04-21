package br.com.promomap.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.promomap.beans.transport.UserObject;
import br.com.promomap.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRest {

	@Autowired
	UserService userService;
	
	@RequestMapping("/list")
	public List<UserObject> list() {
		return userService.listAll();
	}
	
	@RequestMapping("/create")
	public UserObject create(@RequestParam(value = "name") String name) {
		return userService.create(new UserObject().setName(name));
	}
	
	@RequestMapping("/delete")
	public void delete(@RequestParam(value = "superId") String superId) {
		userService.delete(new UserObject().setSuperId(superId));
	}
}
