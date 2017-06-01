/*
 * @(#)Vertex.java 28 de mai de 2017 - 21:47:33
 *
 */
package br.com.promomap.model.graph;

import java.util.ArrayList;
import java.util.List;

import br.com.promomap.beans.transport.CompanyObject;
import br.com.promomap.beans.transport.ProductObject;
import br.com.promomap.model.enums.CategoryEnum;
import br.com.promomap.model.enums.VertexTypeEnum;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
public class Vertex {

	private final int id;
	private final VertexTypeEnum type;
	private CategoryEnum category;
	private CompanyObject company;
	private List<ProductObject> products;

	public Vertex(int id, VertexTypeEnum type, CategoryEnum categoty) {
		this.id = id;
		this.type = type;
		this.category = categoty;
	}
	
	public Vertex(int id, VertexTypeEnum type) {
		this.id = id;
		this.type = type;
		this.products = new ArrayList<ProductObject>();
	}

	public int getId() {
		return id;
	}

	public VertexTypeEnum getType() {
		return type;
	}

	public CategoryEnum getCategory() {
		return category;
	}

	public void setCategory(CategoryEnum category) {
		this.category = category;
	}

	public CompanyObject getCompany() {
		return company;
	}

	public void setCompany(CompanyObject company) {
		this.company = company;
	}

	public List<ProductObject> getProducts() {
		return products;
	}

	public void setProducts(List<ProductObject> products) {
		this.products = products;
	}

}
