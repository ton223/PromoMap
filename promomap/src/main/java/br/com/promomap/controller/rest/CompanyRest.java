/*
 * @(#)CompanyRest.java 10 de mai de 2017 - 23:19:05
 *
 */
package br.com.promomap.controller.rest;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.promomap.beans.persistence.User;
import br.com.promomap.beans.transport.CompanyObject;
import br.com.promomap.beans.transport.TaskObject;
import br.com.promomap.service.CompanyService;
import br.com.promomap.service.UserService;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
@RestController
@RequestMapping("/company")
public class CompanyRest {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private UserService userService;

	@PostMapping()
	public Response create(@RequestHeader("token") String token, @RequestBody CompanyObject companyO) {
		if (token == null || token.equals("null") || token.isEmpty()) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		User user = this.userService.findUserBySessionToken(token);
		TaskObject task = this.companyService.create(user, companyO);
		return Response.ok(task).build();
	}

	@GetMapping()
	public Response list(@RequestHeader("token") String token) {
		if (token == null || token.equals("null") || token.isEmpty()) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		User user = this.userService.findUserBySessionToken(token);
		TaskObject task = companyService.listByUser(user);
		return Response.ok(task).build();
	}
	
	@DeleteMapping("/{companyId}")
	public Response delete(@RequestHeader("token") String token,@PathVariable("companyId") String companyId) {
		if (token == null || token.equals("null") || token.isEmpty()) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		User user = this.userService.findUserBySessionToken(token);
		if(user == null) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		TaskObject task = companyService.delete(companyId, user);
		return Response.ok(task).build();
	}
}
