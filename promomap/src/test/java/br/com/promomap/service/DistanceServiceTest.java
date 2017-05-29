/*
 * @(#)MapServiceTest.java 28 de mai de 2017 - 16:21:15
 *
 */
package br.com.promomap.service;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.promomap.beans.transport.LocationObject;
import br.com.promomap.model.enums.TravelModeEnum;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DistanceServiceTest {
	
	@Autowired
	DistanceService distanceService;

	@Test
	public void test() throws IOException {
		LocationObject origin = new LocationObject();
		origin.setLat("-8.018448731073946");
		origin.setLng("-34.87036675214762");

		LocationObject destination = new LocationObject();
		destination.setLat("-8.162249160585379");
		destination.setLng("-34.91621106863016");

		distanceService.configure(origin, destination, TravelModeEnum.DRIVING);
		System.out.println("Origem: " + distanceService.getOriginAddress());
		System.out.println("Destino: " + distanceService.getDestinationAddress());
		System.out.println("Locomoção: " + distanceService.getTravelMode().getDescription());
		System.out.println("Distancia: " + distanceService.getDistance() + " km");
		System.out.println("Duração: " + distanceService.getDuration());
	}
}
