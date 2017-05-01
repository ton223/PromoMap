package br.com.promomap.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.promomap.beans.persistence.Session;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */

public interface SessionDAO extends CrudRepository<Session, Long> {
	
	@Query("from Session where logged=1 and token=:token")
	public Session getByToken(@Param("token")String token);
	
}
