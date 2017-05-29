/*
 * @(#)CategoryEnum.java 28 de mai de 2017 - 21:01:47
 *
 */
package br.com.promomap.model.enums;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
public enum CategoryEnum {

	ELETRONIC("Eletrônicos e celulares"),
	HOME_APPLIANCES("Eletrodomésticos"),
	FOOD("Alimentação"),
	TOYS("Brinquedos"),
	GAMES("Jogos"),
	AUTO("Veículos e barcos"),
	SPORT("Esporte"),
	FASHION("Moda e beleza"),
	KIDS("Bebês e crianças"),
	HOME("Para a sua casa"),
	MUSIC("Músicas e hobbies"),
	;

	private final String description;

	private CategoryEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static CategoryEnum findFromDescription(String description) {
		for(CategoryEnum category : CategoryEnum.values()) {
			if(category.getDescription().equals(description)) {
				return category;
			}
		}
		return null;
	}
}
