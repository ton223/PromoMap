/*
 * @(#)CompanyDAO.java 13 de mai de 2017 - 17:29:19
 *
 */
package br.com.promomap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.promomap.beans.persistence.Company;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
public interface CompanyDAO extends CrudRepository<Company, Long>{
	
	public Company findBySuperId(String superId);
	
	@Query("from Company where deleted!=1")
	public  List<Company> listAll();

}
