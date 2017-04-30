package br.com.promomap.controller;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.promomap.beans.transport.RequestObject;
import br.com.promomap.beans.transport.TaskObject;
import br.com.promomap.beans.transport.UserObject;
import br.com.promomap.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRest {

	@Autowired
	UserService userService;

	// @RequestMapping("/list")
	// public List<UserObject> list() {
	// return userService.listAll();
	// }

	@PostMapping("/create")
	public Response create(@RequestBody UserObject user) {
		TaskObject task = this.userService.create(user);
		return Response.ok(task).build();
	}

	@PostMapping("/login")
	public Response login(@RequestBody UserObject user) {
		TaskObject task = this.userService.login(user);
		return Response.ok(task).build();
	}

	@PostMapping("/logout")
	public Response logout(@RequestBody RequestObject request) { //, defaultValue="no-token"
		String token = request.getToken();
		TaskObject task = this.userService.logout(token);
		return Response.ok(task).build();
	}

	// @RequestMapping("/delete")
	// public void delete(@RequestParam(value = "superId") String superId) {
	// userService.delete(new UserObject().setSuperId(superId));
	// }
}
