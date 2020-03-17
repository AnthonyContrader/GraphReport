package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.DataSetDTO;
import it.contrader.model.DataSet;

@Component
public class DataSetConverter extends AbstractConverter<DataSet, DataSetDTO>{

	
	public DataSetDTO toDTO(DataSet dato) {
		
		DataSetDTO datoDTO = new DataSetDTO(dato.getId(), dato.getUtente(), dato.getCategoria(), dato.getUnitaMisura(), dato.getValore());
		return datoDTO;
	}

	
	public DataSet toEntity(DataSetDTO datoDTO) {
		DataSet dato = new DataSet(datoDTO.getId(),datoDTO.getUtente(), datoDTO.getCategoria(), datoDTO.getUnitaMisura(), datoDTO.getValore());
		return dato;
	}
	
}
