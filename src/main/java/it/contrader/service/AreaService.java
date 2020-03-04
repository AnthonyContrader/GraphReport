package it.contrader.service;

import java.util.List;


import it.contrader.converter.AreaConverter;
import it.contrader.dao.AreaDAO;
import it.contrader.dto.AreaDTO;

/**
 * 
 *Grazie all'ereditarietà mi basta specificare i tipi di questa classe per
 *ereditare i metodi della clase AbstractService. Pertanto la classe risulta meno complicata
 *da scrivere, facendoci risparmiare tempo e fatica!
 */
public class AreaService {
	
	private AreaDAO areaDAO;
	private AreaConverter areaConverter;
	
	//Istanzio DAO  e Converter specifici.
	public AreaService(){
		this.areaDAO = new AreaDAO();
		this.areaConverter = new AreaConverter();
	}
	

	public List<AreaDTO> getAll() {
		// Ottiene una lista di entità e le restituisce convertendole in DTO
		return areaConverter.toDTOList(areaDAO.getAll());
	}


	public AreaDTO read(int id) {
		// Ottiene un'entità e la restituisce convertendola in DTO
		return areaConverter.toDTO(areaDAO.read(id));
	}
	
	public Integer id(String nome) {
		return areaDAO.getId(nome);
	}


	public boolean insert(AreaDTO dto) {
		// Converte un DTO in entità e lo passa al DAO per l'inserimento
		return areaDAO.insert(areaConverter.toEntity(dto));
	}


	public boolean update(AreaDTO dto) {
		// Converte un userDTO in entità e lo passa allo userDAO per la modifica
		return areaDAO.update(areaConverter.toEntity(dto));
	}


	public boolean delete(int id) {
		// Questo mtodo chiama direttamente il DAO
		return areaDAO.delete(id);
	}
	

}
