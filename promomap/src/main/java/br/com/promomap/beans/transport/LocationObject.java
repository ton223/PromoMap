/*
 * @(#)LocationObject.java 13 de mai de 2017 - 17:34:57
 *
 */
package br.com.promomap.beans.transport;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
public class LocationObject {
	
	private String superId;
	
	private String lat;
	
	private String lng;

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
