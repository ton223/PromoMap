/*
 * @(#)ProductDAO.java 20 de mai de 2017 - 15:52:30
 *
 */
package br.com.promomap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.promomap.beans.persistence.Product;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
public interface ProductDAO extends CrudRepository<Product, Long> {

	public Product findBySuperId(String superId);
	
	@Query("from Product where deleted!=1 and company_id=:id")
	public List<Product> listByCompany(@Param("id")Long companyId);
}
