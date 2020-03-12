package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.UnitaMisuraDTO;
import it.contrader.model.UnitaMisura;

public class UnitaMisuraConverter {

	public UnitaMisuraDTO toDTO(UnitaMisura unitamisura) {
		UnitaMisuraDTO unitamisuraDTO = new UnitaMisuraDTO(unitamisura.getId(), unitamisura.getNome());
		return unitamisuraDTO;
	}

	public UnitaMisura toEntity(UnitaMisuraDTO unitamisuraDTO) {
		UnitaMisura unitamisura = new UnitaMisura(unitamisuraDTO.getId(), unitamisuraDTO.getNome());
		return unitamisura;
	}
	
	public List<UnitaMisuraDTO> toDTOList(List<UnitaMisura> unitamisuraList) {
		List<UnitaMisuraDTO> unitamisuraDTOList = new ArrayList<UnitaMisuraDTO>();
		
		for(UnitaMisura unitamisura : unitamisuraList) {
			unitamisuraDTOList.add(toDTO(unitamisura));
		}
		return unitamisuraDTOList;
	}
}
