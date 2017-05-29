/*
 * @(#)LocationServiceTest.java 28 de mai de 2017 - 20:21:24
 *
 */
package br.com.promomap.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.promomap.beans.transport.LocationObject;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationServiceTest {

	@Autowired
	private LocationService locationService;
	
	@Test
	public void getLocationsInRadiusTest() {
		LocationObject origin = new LocationObject();
		origin.setLat("-8.01844497494305");
		origin.setLng("-34.870361387729645");
		locationService.getLocationsInRadius(origin).forEach(l -> {
			System.out.println(l.getSuperId());
		});
	}
}
