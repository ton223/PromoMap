/*
 * @(#)Arris.java 28 de mai de 2017 - 21:57:10
 *
 */
package br.com.promomap.model.graph;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
public class Arris {

	private Vertex vertexA;

	private Vertex vertexB;

	private BigDecimal distance;

	public Vertex getVertexA() {
		return vertexA;
	}

	public void setVertexA(Vertex vertexA) {
		this.vertexA = vertexA;
	}

	public Vertex getVertexB() {
		return vertexB;
	}

	public void setVertexB(Vertex vertexB) {
		this.vertexB = vertexB;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

}
