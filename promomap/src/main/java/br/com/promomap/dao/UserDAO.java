package br.com.promomap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.promomap.beans.persistence.User;

public interface UserDAO extends CrudRepository<User, Long>{
	
	public User findBySuperId(String superId);
	
	public User findByEmail(String email);
	
	public User findByEmailAndPassword(String email, String password);
	
	@Query("from User where deleted!=1")
	public  List<User> listAll();
	
//	Exemplo de custom query:
//	@Query("from Auction a join a.category c where c.name=:categoryName")
//	public Iterable<Auction> findByCategory(@Param("categoryName") String categoryName);

}
