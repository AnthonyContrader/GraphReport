package it.contrader.service;

import java.util.List;

import it.contrader.converter.DatoConverter;
import it.contrader.dao.DatoDAO;
import it.contrader.dto.DatoDTO;

/**
 *Grazie all'ereditarietà mi basta specificare i tipi di questa classe per
 *ereditare i metodi della clase AbstractService. Pertanto la classe risulta meno complicata
 *da scrivere, facendoci risparmiare tempo e fatica!
 */
public class DatoService {
	
	private DatoDAO datoDAO;
	private DatoConverter datoConverter;
	
	//Istanzio DAO  e Converter specifici.
	public DatoService(){
		this.datoDAO = new DatoDAO();
		this.datoConverter = new DatoConverter();
	}
	

	public List<DatoDTO> getAll() {
		// Ottiene una lista di entità e le restituisce convertendole in DTO
		return datoConverter.toDTOList(datoDAO.getAll());
	}


	public DatoDTO read(int id) {
		// Ottiene un'entità e la restituisce convertendola in DTO
		return datoConverter.toDTO(datoDAO.read(id));
	}


	public boolean insert(DatoDTO dto) {
		// Converte un DTO in entità e lo passa al DAO per l'inserimento
		return datoDAO.insert(datoConverter.toEntity(dto));
	}


	public boolean update(DatoDTO dto) {
		// Converte un DTO in entità e lo passa allo DAO per la modifica
		return datoDAO.update(datoConverter.toEntity(dto));
	}


	public boolean delete(int id) {
		// Questo mtodo chiama direttamente il DAO
		return datoDAO.delete(id);
	}
	

}
