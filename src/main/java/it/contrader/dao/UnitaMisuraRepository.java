package it.contrader.dao;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import it.contrader.model.UnitaMisura;


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
public interface UnitaMisuraRepository extends CrudRepository<UnitaMisura, Long>{

	UnitaMisura findByNome(String nome);

	@Query("select s from UnitaMisura s where s.nome =?1")
	List<UnitaMisura> findAll(String daCercare);
	
}
