package it.contrader.dao;

import javax.transaction.Transactional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import it.contrader.model.User;


/**
 * Estende CrudRepository ed eredita tutti i metodi di CRUD. 
 * Definisce il metodo di login.
 * 
 * @author Vittorio Valent & Girolamo Murdaca
 *
 * @see CrudRepository
 *
 */
@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long>{

	User findByUsernameAndPassword(String username, String password);

	@Query("select s from User s where s.username =?1 or s.nome =?1 or s.cognome = ?1 or s.citta =?1 or s.nazione =?1")
	List<User> findAll(String daCercare);

	
}
