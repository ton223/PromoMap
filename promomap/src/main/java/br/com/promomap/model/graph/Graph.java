/*
 * @(#)Graph.java 28 de mai de 2017 - 21:58:39
 *
 */
package br.com.promomap.model.graph;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import br.com.promomap.beans.transport.CompanyObject;
import br.com.promomap.beans.transport.ProductObject;
import br.com.promomap.model.enums.CategoryEnum;
import br.com.promomap.model.enums.VertexTypeEnum;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
public class Graph {

	private final Vertex origin;
	private List<Vertex> vertexs;
	private List<Arris> edges;

	public Graph() {
		this.origin = new Vertex(new Random().hashCode(), VertexTypeEnum.ORIGIN);
		vertexs = new ArrayList<Vertex>();
		edges = new ArrayList<Arris>();
	}
	
	public Graph mount(Map<CategoryEnum , List<ProductObject>> products, Map<CompanyObject, BigDecimal> distances) {
		List<Vertex> categoryVertexs = new ArrayList<Vertex>();
		for(CategoryEnum category : CategoryEnum.values()) {
			if(products.get(category) != null) {
				Vertex vertex = new Vertex(new Random().hashCode(), VertexTypeEnum.CATEGORY, category);
				this.vertexs.add(vertex);
				categoryVertexs.add(vertex);
				Arris arris = new Arris();
				arris.setVertexA(origin);
				arris.setVertexB(vertex);
				edges.add(arris);				
			}
		}
		for(Vertex v : categoryVertexs) {
			Map<CompanyObject, Vertex> vertexByCompany = new HashMap<CompanyObject, Vertex>();
			for(ProductObject product : products.get(v.getCategory())) {
				Vertex vertexExist = vertexByCompany.get(product.getCompany());
				if(vertexExist != null) {
					vertexExist.getProducts().add(product);
					continue;
				}
				Vertex vertex = new Vertex(new Random().hashCode(), VertexTypeEnum.COMPANY);
				vertex.getProducts().add(product);
				vertex.setCompany(product.getCompany());
				this.vertexs.add(vertex);
				vertexByCompany.put(product.getCompany(), vertex);
				Arris arris = new Arris();
				arris.setVertexA(v);
				arris.setVertexB(vertex);
				arris.setDistance(distances.get(vertex.getCompany()));
				this.edges.add(arris);				
			}
		}
		return this;
	}

	public Vertex getOrigin() {
		return origin;
	}

	public List<Vertex> getVertexs() {
		return vertexs;
	}

	public List<Arris> getArris() {
		return edges;
	}

}
