package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.contrader.converter.CategoriaConverter;
import it.contrader.dao.CategoriaRepository;
import it.contrader.dto.CategoriaDTO;
import it.contrader.model.Categoria;


@Service
public class CategoriaService extends AbstractService<Categoria, CategoriaDTO>{
	
	@Autowired
	private CategoriaConverter converter;
	@Autowired
	private CategoriaRepository repository;
	
	public CategoriaDTO findByNome(String nome) {
		return converter.toDTO(repository.findByNome(nome));
	}
	

}
