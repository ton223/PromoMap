/*
 * @(#)ProductObject.java 20 de mai de 2017 - 15:01:55
 *
 */
package br.com.promomap.beans.transport;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
public class ProductObject {

	private String superId;

	private String code;

	private String name;

	private String description;

	private BigDecimal price;

	private Integer rating;

	private CompanyObject company;

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public CompanyObject getCompany() {
		return company;
	}

	public void setCompany(CompanyObject company) {
		this.company = company;
	}
}
