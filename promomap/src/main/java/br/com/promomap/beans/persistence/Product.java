/*
 * @(#)Product.java 19 de mai de 2017 - 13:19:11
 *
 */
package br.com.promomap.beans.persistence;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.promomap.beans.transport.ProductObject;
import br.com.promomap.model.TransportObjectInterface;
import br.com.promomap.model.enums.CategoryEnum;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
@Entity
@Table(name = "Product")
public class Product implements TransportObjectInterface<ProductObject> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "superId", nullable = false, unique = true)
	private String superId;

	@Column(name = "imagePublicId")
	private String imagePublicId;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "discount")
	private BigDecimal discount;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "category")
	@Enumerated(EnumType.STRING)
	private CategoryEnum category;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt")
	private Date createdAt;

	@Column(name = "deleted", columnDefinition = "tinyint DEFAULT 0")
	private boolean deleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deletedDate")
	private Date deletedDate;

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

	public CategoryEnum getCategory() {
		return category;
	}

	public void setCategory(CategoryEnum category) {
		this.category = category;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	@Override
	public ProductObject generateTransportObject() {
		ProductObject productO = new ProductObject();
		productO.setSuperId(getSuperId());
		productO.setImagePublicId(getImagePublicId());
		productO.setName(getName());
		productO.setDescription(getDescription());
		productO.setPrice(getPrice());
		productO.setRating(getRating());
		productO.setDiscount(getDiscount());
		productO.setCategory(getCategory().getDescription());
		productO.setCompany(getCompany().generateTransportObject());
		productO.setCode(getCode());
		return productO;
	}

}
