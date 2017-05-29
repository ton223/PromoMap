/*
 * @(#)ProductRest.java 29 de mai de 2017 - 00:39:07
 *
 */
package br.com.promomap.controller.rest;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.promomap.beans.transport.LocationObject;
import br.com.promomap.beans.transport.TaskObject;
import br.com.promomap.service.ProductService;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
@RestController
@RequestMapping("/product")
public class ProductRest {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping()
	public Response list(@RequestHeader("token") String token,  @RequestBody LocationObject userLocation) {
		TaskObject task = productService.listProductsArround(userLocation);
		return Response.ok(task).build();
	}

}
