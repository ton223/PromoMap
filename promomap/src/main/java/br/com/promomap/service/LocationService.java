/*
 * @(#)LocationService.java 28 de mai de 2017 - 18:32:40
 *
 */
package br.com.promomap.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.promomap.beans.persistence.Location;
import br.com.promomap.beans.transport.LocationObject;
import br.com.promomap.dao.LocationDAO;
import br.com.promomap.utils.Utils;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
@Service
public class LocationService {
	
	private final BigDecimal KM = new BigDecimal("0.00909483");
	private final BigDecimal MAX_DISTANCE = new BigDecimal("60").multiply(KM); 
	
	@Autowired 
	private LocationDAO locationDAO;
	
	public Location create(LocationObject locationO) {
		Location location = new Location();
		location.setSuperId(Utils.generateRandomUUID());
		location.setLat(locationO.getLat());
		location.setLng(locationO.getLng());
		locationDAO.save(location);
		return locationDAO.findBySuperId(location.getSuperId());
	}
	
	public void delete(Location location) {
		location.setDeleted(true);
		location.setDeletedDate(new Date());
		this.locationDAO.save(location);
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<Location> getLocationsInRadius(LocationObject origin) {
		BigDecimal xa = new BigDecimal(origin.getLat());
		BigDecimal ya = new BigDecimal(origin.getLng());
		Set<Location> locations = new HashSet<Location>();
		this.locationDAO.listAll().forEach(l -> {
			BigDecimal xb = new BigDecimal(l.getLat());
			BigDecimal yb = new BigDecimal(l.getLng());
			BigDecimal xResult = xb.subtract(xa);
			BigDecimal yResult = yb.subtract(ya);
			xResult = xResult.multiply(xResult);
			yResult = yResult.multiply(yResult);
			BigDecimal sumResult = xResult.add(yResult);
			BigDecimal finalResult = new BigDecimal(Math.sqrt(sumResult.doubleValue()));
			if(finalResult.compareTo(MAX_DISTANCE) == -1) {
				locations.add(l);
			}
		});
		return locations;
	}
}
