/*
 * @(#)TagDAO.java 20 de mai de 2017 - 16:40:43
 *
 */
package br.com.promomap.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.promomap.beans.persistence.Tag;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
public interface TagDAO extends CrudRepository<Tag, Long> {

	public Tag findByName(String name);
	
}
