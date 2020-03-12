package it.contrader.service;

import it.contrader.converter.UnitaMisuraConverter;
import it.contrader.dao.UnitaMisuraDAO;
import it.contrader.dto.UnitaMisuraDTO;
import it.contrader.model.UnitaMisura;



public class UnitaMisuraService extends AbstractService<UnitaMisura, UnitaMisuraDTO> {
	
	
	public UnitaMisuraService(){
		this.dao = new UnitaMisuraDAO();
		this.converter= new UnitaMisuraConverter();
	}
	

	public Integer getId(String nome) {
		UnitaMisuraDAO unitmis = new UnitaMisuraDAO();
		return unitmis.getId(nome);
	}

}
