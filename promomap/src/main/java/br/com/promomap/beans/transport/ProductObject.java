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

	private String imagePublicId;

	private String code;

	private String name;

	private String description;

	private BigDecimal price;

	private BigDecimal discount;

	private Integer rating;

	private String category;

	private CompanyObject company;

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public String getImagePublicId() {
		return imagePublicId;
	}

	public void setImagePublicId(String imagePublicId) {
		this.imagePublicId = imagePublicId;
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

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public CompanyObject getCompany() {
		return company;
	}

	public void setCompany(CompanyObject company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((superId == null) ? 0 : superId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductObject other = (ProductObject) obj;
		if (superId == null) {
			if (other.superId != null)
				return false;
		} else if (!superId.equals(other.superId))
			return false;
		return true;
	}
}
