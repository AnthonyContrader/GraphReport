package it.contrader.service;

import it.contrader.converter.CategoriaConverter;
import it.contrader.dao.CategoriaDAO;
import it.contrader.dto.CategoriaDTO;
import it.contrader.model.Categoria;

/**
 * 
 *Grazie all'ereditarietà mi basta specificare i tipi di questa classe per
 *ereditare i metodi della clase AbstractService. Pertanto la classe risulta meno complicata
 *da scrivere, facendoci risparmiare tempo e fatica!
 */
public class CategoriaService extends AbstractService<Categoria, CategoriaDTO> {
	
	//Istanzio DAO  e Converter specifici.
	public CategoriaService(){
		this.dao = new CategoriaDAO();
		this.converter = new CategoriaConverter();
	}
	
	
	public Integer getId(String nome) {
		CategoriaDAO catdao = new CategoriaDAO();
		return catdao.getId(nome);
	}

}
