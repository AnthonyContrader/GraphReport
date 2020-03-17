package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.contrader.converter.UnitaMisuraConverter;
import it.contrader.dao.UnitaMisuraRepository;
import it.contrader.dto.UnitaMisuraDTO;



@Service
public class UnitaMisuraService extends AbstractService<UnitaMisuraService, UnitaMisuraDTO> {
	
	@Autowired
	private UnitaMisuraConverter converter;
	@Autowired
	private UnitaMisuraRepository repository;
	
	public UnitaMisuraDTO findByNome(String nome) {
		return converter.toDTO(repository.findByNome(nome));
	}

}
