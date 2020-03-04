package it.contrader.service;

import java.util.List;


import it.contrader.converter.UtenteConverter;
import it.contrader.dao.UtenteDAO;
import it.contrader.dto.UtenteDTO;

/**
 * 
 * @author Vittorio, De Santis
 *
 *Grazie all'ereditarietà mi basta specificare i tipi di questa classe per
 *ereditare i metodi della clase AbstractService. Pertanto la classe risulta meno complicata
 *da scrivere, facendoci risparmiare tempo e fatica!
 */
public class UtenteService {
	
	private UtenteDAO utenteDAO;
	private UtenteConverter utenteConverter;
	
	//Istanzio DAO  e Converter specifici.
	public UtenteService(){
		this.utenteDAO = new UtenteDAO();
		this.utenteConverter = new UtenteConverter();
	}
	

	public List<UtenteDTO> getAll() {
		// Ottiene una lista di entità e le restituisce convertendole in DTO
		return utenteConverter.toDTOList(utenteDAO.getAll());
	}


	public UtenteDTO read(int id) {
		// Ottiene un'entità e la restituisce convertendola in DTO
		return utenteConverter.toDTO(utenteDAO.read(id));
	}


	public boolean insert(UtenteDTO dto) {
		// Converte un DTO in entità e lo passa al DAO per l'inserimento
		return utenteDAO.insert(utenteConverter.toEntity(dto));
	}


	public boolean update(UtenteDTO dto) {
		// Converte un userDTO in entità e lo passa allo userDAO per la modifica
		return utenteDAO.update(utenteConverter.toEntity(dto));
	}


	public boolean delete(int id) {
		// Questo mtodo chiama direttamente il DAO
		return utenteDAO.delete(id);
	}
	
}
