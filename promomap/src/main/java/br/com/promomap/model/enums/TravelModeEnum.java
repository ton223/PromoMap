/*
 * @(#)TravelModeEnum.java 28 de mai de 2017 - 16:39:23
 *
 */
package br.com.promomap.model.enums;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
public enum TravelModeEnum {

	DRIVING("driving", "Carro"), 
	WALKING("walking", "A pé"), 
	BICYCLING("bicycling", "bicicleta"), 
	TRANSIT("transit", "Transporte público");

	private final String mode, description;

	private TravelModeEnum(String mode, String description) {
		this.mode = mode;
		this.description = description;
	}

	public String getMode() {
		return mode;
	}

	public String getDescription() {
		return description;
	}

	public static TravelModeEnum findFromMode(String mode) {
		for (TravelModeEnum travelMode : TravelModeEnum.values()) {
			if (travelMode.getMode().equals(mode)) {
				return travelMode;
			}
		}
		return null;
	}

}
