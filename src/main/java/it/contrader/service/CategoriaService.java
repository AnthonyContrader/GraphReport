package it.contrader.service;

import java.util.ArrayList;
import java.util.List;

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
public class CategoriaService {
	
	private CategoriaDAO categoriaDAO;
	private CategoriaConverter categoriaConverter;
	
	//Istanzio DAO  e Converter specifici.
	public CategoriaService(){
		this.categoriaDAO = new CategoriaDAO();
		this.categoriaConverter = new CategoriaConverter();
	}
	

	public List<CategoriaDTO> getAll() {
		// Ottiene una lista di entità e le restituisce convertendole in DTO
		return categoriaConverter.toDTOList(categoriaDAO.getAll());
	}


	public CategoriaDTO read(int id) {
		// Ottiene un'entità e la restituisce convertendola in DTO
		return categoriaConverter.toDTO(categoriaDAO.read(id));
	}
	
	public Integer getId(String nome) {
		return categoriaDAO.getId(nome);
	}


	public boolean insert(CategoriaDTO dto) {
		// Converte un DTO in entità e lo passa al DAO per l'inserimento
		return categoriaDAO.insert(categoriaConverter.toEntity(dto));
	}


	public boolean update(CategoriaDTO dto) {
		// Converte un categoriaDTO in entità e lo passa allo categoriaDAO per la modifica
		return categoriaDAO.update(categoriaConverter.toEntity(dto));
	}


	public boolean delete(int id) {
		// Questo mtodo chiama direttamente il DAO
		return categoriaDAO.delete(id);
	}
	

}
