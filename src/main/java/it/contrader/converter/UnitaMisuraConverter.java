package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.UnitaMisuraDTO;

import it.contrader.model.UnitaMisura;


@Component
public class UnitaMisuraConverter extends AbstractConverter <UnitaMisura, UnitaMisuraDTO> {

	@Override
	public UnitaMisura toEntity(UnitaMisuraDTO unitamisuraDTO) {
		UnitaMisura unitamisura = null;
		if(unitamisuraDTO != null) {
			unitamisura = new UnitaMisura(unitamisuraDTO.getId(), unitamisuraDTO.getNome());
		}
		return unitamisura;
	}
	
	@Override
	public UnitaMisuraDTO toDTO(UnitaMisura unitamisura) {
		UnitaMisuraDTO unitamisuraDTO = null;
		if(unitamisura != null) {
			unitamisuraDTO = new UnitaMisuraDTO(unitamisura.getId(), unitamisura.getNome());	
		}
		return unitamisuraDTO;
	}
}
