package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.DataSetDTO;
import it.contrader.model.Categoria;
import it.contrader.model.DataSet;
import it.contrader.model.UnitaMisura;
import it.contrader.model.User;

@Component
public class DataSetConverter extends AbstractConverter<DataSet, DataSetDTO>{

	
	public DataSetDTO toDTO(DataSet dato) {
		
		DataSetDTO datoDTO = new DataSetDTO(
				dato.getId(),
				dato.getUtente().getId(),
				dato.getCategoria().getId(),
				dato.getCategoria().getNome(),
				dato.getUnitaMisura().getId(),
				dato.getUnitaMisura().getNome(),
				dato.getValore()
			);

		return datoDTO;
	}

	
	public DataSet toEntity(DataSetDTO datoDTO) {
		DataSet dato = new DataSet(
					datoDTO.getId(), 
					new User(datoDTO.getUtente(),null,null,null,null,null,null,null,null), 
					new Categoria(datoDTO.getCategoria(),null), 
					new UnitaMisura(datoDTO.getUnitaMisura(),null), 
					datoDTO.getValore()
				);
		return dato;
	}
	
}
