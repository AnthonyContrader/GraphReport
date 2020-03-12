package it.contrader.service;

import java.util.ArrayList;
import java.util.List;

import it.contrader.converter.UnitaMisuraConverter;
import it.contrader.dao.UnitaMisuraDAO;
import it.contrader.dto.UnitaMisuraDTO;
import it.contrader.model.UnitaMisura;


public class UnitaMisuraService {
	
	private UnitaMisuraDAO unitamisuraDAO;
	private UnitaMisuraConverter unitamisuraConverter;
	
	public UnitaMisuraService(){
		this.unitamisuraDAO = new UnitaMisuraDAO();
		this.unitamisuraConverter = new UnitaMisuraConverter();
	}
	

	public List<UnitaMisuraDTO> getAll() {
		return unitamisuraConverter.toDTOList(unitamisuraDAO.getAll());
	}


	public UnitaMisuraDTO read(int id) {
		return unitamisuraConverter.toDTO(unitamisuraDAO.read(id));
	}


	public boolean insert(UnitaMisuraDTO dto) {
		return unitamisuraDAO.insert(unitamisuraConverter.toEntity(dto));
	}


	public boolean update(UnitaMisuraDTO dto) {
		return unitamisuraDAO.update(unitamisuraConverter.toEntity(dto));
	}


	public boolean delete(int id) {
		return unitamisuraDAO.delete(id);
	}
	
	public Integer getId(String nome) {
		return unitamisuraDAO.getId(nome);
	}

}
