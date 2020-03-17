package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.UnitaMisura;

@Repository
@Transactional
public interface UnitaMisuraRepository extends CrudRepository <UnitaMisura, Long> {

	UnitaMisura findByNome(String nome);
}
