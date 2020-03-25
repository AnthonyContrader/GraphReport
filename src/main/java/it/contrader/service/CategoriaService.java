package it.contrader.service;

import org.springframework.stereotype.Service;

import it.contrader.dao.CategoriaRepository;
import it.contrader.dto.CategoriaDTO;
import it.contrader.model.Categoria;

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
public class CategoriaService extends AbstractService<Categoria,CategoriaDTO> {
	
	//ALL crud methods in AbstractService
	
	public CategoriaDTO findByNome(String nome) {
		return converter.toDTO(((CategoriaRepository)repository).findByNome(nome));
	}

}
