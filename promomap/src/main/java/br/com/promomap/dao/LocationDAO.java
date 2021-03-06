/*
 * @(#)CompanyDAO.java 13 de mai de 2017 - 17:29:19
 *
 */
package br.com.promomap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.promomap.beans.persistence.Location;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
public interface LocationDAO extends CrudRepository<Location, Long> {

	@Query("from Location where deleted!=1")
	public List<Location> listAll();
	
	public Location findBySuperId(String superId);

	public Location findById(Long id);

}
