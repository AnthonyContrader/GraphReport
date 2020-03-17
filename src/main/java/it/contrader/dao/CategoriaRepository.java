package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Categoria;

@Repository
@Transactional

public interface CategoriaRepository extends CrudRepository <Categoria,Long> {
	
	Categoria findByNome(String nome);
}

