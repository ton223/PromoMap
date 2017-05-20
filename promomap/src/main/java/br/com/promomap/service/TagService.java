/*
 * @(#)TagService.java 20 de mai de 2017 - 16:44:21
 *
 */
package br.com.promomap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.promomap.beans.persistence.Tag;
import br.com.promomap.beans.transport.TagObject;
import br.com.promomap.dao.TagDAO;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */
@Service
public class TagService {
	
	@Autowired
	private TagDAO tagDAO;

	public Tag create(TagObject tagO) {
		Tag tag = new Tag();
		tag.setName(tagO.getName());
		tag.setDescription(tagO.getDescription());
		tagDAO.save(tag);
		
		return this.tagDAO.findByName(tag.getName());
	}
}
