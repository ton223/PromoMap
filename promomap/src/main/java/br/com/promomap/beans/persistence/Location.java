/*
 * @(#)Location.java 13 de mai de 2017 - 17:33:05
 *
 */
package br.com.promomap.beans.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.promomap.beans.transport.LocationObject;
import br.com.promomap.model.PersistenceBeanInterface;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
@Entity
@Table(name = "Location")
public class Location implements PersistenceBeanInterface<LocationObject> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "superId", nullable = false, unique = true)
	private String superId;
	
	@Column(name = "latitude", nullable = false)
	private String lat;
	
	@Column(name = "longitude", nullable = false)
	private String lng;

	@Override
	public LocationObject generateTransportObject() {
		LocationObject locationO = new LocationObject();
		locationO.setSuperId(getSuperId());
		locationO.setLat(getLat());
		locationO.setLng(getLng());
		return locationO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
}
