package br.com.promomap.controller;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.promomap.beans.persistence.User;
import br.com.promomap.beans.transport.TaskObject;
import br.com.promomap.beans.transport.UserObject;
import br.com.promomap.service.UserService;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */

@RestController
@RequestMapping("/user")
public class UserRest {

	@Autowired
	UserService userService;

	// @RequestMapping("/list")
	// public List<UserObject> list() {
	// return userService.listAll();
	// }

	@PostMapping()
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
	public Response logout(@RequestHeader("token") String token) {
		TaskObject task = this.userService.logout(token);
		return Response.ok(task).build();
	}
	
	@PutMapping()
	public Response edit(@RequestHeader("token") String token, @RequestBody UserObject userEdited) {
		if(token == null || token.equals("null") || token.isEmpty()) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		User user = userService.findUserBySessionToken(token);
		TaskObject task = this.userService.edit(user, userEdited);
		return Response.ok(task).build();
	}

	// @RequestMapping("/delete")
	// public void delete(@RequestParam(value = "superId") String superId) {
	// userService.delete(new UserObject().setSuperId(superId));
	// }
}
