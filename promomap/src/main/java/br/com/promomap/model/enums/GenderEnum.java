/*
 * @(#)GenderEnum.java 14 de mai de 2017 - 17:01:58
 *
 */
package br.com.promomap.model.enums;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
public enum GenderEnum {
	MALE("Masculino"),
	FEMALE("Feminino"),
	OTHER("Outro");
	
	private String description;

	private GenderEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static GenderEnum fromDescription(String description) {
		for(GenderEnum gender : GenderEnum.values()) {
			if(gender.getDescription().equals(description)) {
				return gender;
			}
		}
		return null;
	}
	
}
