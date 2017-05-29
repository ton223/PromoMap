/*
 * @(#)MapService.java 28 de mai de 2017 - 15:58:14
 *
 */
package br.com.promomap.service;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.promomap.beans.transport.LocationObject;
import br.com.promomap.model.enums.TravelModeEnum;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
@Service
public class DistanceService {
	private static final String API_KEY = "AIzaSyBqo98hmF-8oiavOEWtd_MNSb6QV3kXcmM";

	private OkHttpClient client = new OkHttpClient();

	private BigDecimal distance;
	private String originAddress;
	private String destinationAddress;
	private String duration;
	private TravelModeEnum travelMode;

	private String run(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();

		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	public void configure(LocationObject origin, LocationObject destination, TravelModeEnum travelMode) throws IOException {
		String origins = origin.getLat()+", "+origin.getLng();
		String destinations = destination.getLat()+", "+destination.getLng();
		String language = "en";
		String retorno = "xml";
		String mode = travelMode.getMode();

		String url_request = "https://maps.googleapis.com/maps/api/distancematrix/" + retorno + "?origins=" + origins
				+ "&destinations=" + destinations + "&mode=" + mode + "&language=" + language + "&key=" + API_KEY;

		String xml = this.run(url_request);

		String distanceTagValue = getTagValue("distance", xml);
		String distanceText = getTagValue("text", distanceTagValue);
		String formattedDistance = null;
		BigDecimal distance = null;
		if(distanceText.contains("km")) {
			formattedDistance = distanceText.replaceAll("km", "").trim();
			distance = new BigDecimal(formattedDistance);
		} else {
			formattedDistance = distanceText.replaceAll("m", "").trim();
			distance = new BigDecimal(formattedDistance).divide(new BigDecimal("1000"));
		}

		this.distance = distance;
		this.originAddress = getTagValue("origin_address", xml);
		this.destinationAddress = this.getTagValue("destination_address", xml);

		String durationTagValue = this.getTagValue("duration", xml);
		this.duration = this.getTagValue("text", durationTagValue);
		this.travelMode = travelMode;
	}

	public BigDecimal getDistance() {
		return this.distance;
	}

	public String getOriginAddress() {
		return originAddress;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public String getDuration() {
		return duration;
	}

	public TravelModeEnum getTravelMode() {
		return travelMode;
	}

	private String getTagValue(String tag, String xml) {

		String tagStart = "<" + tag + ">";
		int indexStart = xml.indexOf(tagStart) + tagStart.length();
		String tagEnd = "</" + tag + ">";
		int indexEnd = xml.indexOf(tagEnd);
		String result = xml.substring(indexStart, indexEnd);

		return result;

	}
}
