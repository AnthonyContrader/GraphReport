package it.contrader.service;

import org.springframework.stereotype.Service;

import it.contrader.dao.UnitaMisuraRepository;
import it.contrader.dto.UnitaMisuraDTO;
import it.contrader.model.UnitaMisura;

/**
 * Estende AbstractService con parametri User e UserDTO. 
 * Implementa il metodo di login ed eredita quelli Abstract. 
 * 
 * @author Vittorio Valent & Girolamo Murdaca
 * 
 * @see AbstractService
 * @see ServiceDTO
 */
@Service
public class UnitaMisuraService extends AbstractService<UnitaMisura,UnitaMisuraDTO> {
	
	//ALL crud methods in AbstractService
	
	public UnitaMisuraDTO findByNome(String nome) {
		return converter.toDTO(((UnitaMisuraRepository)repository).findByNome(nome));
	}

}
