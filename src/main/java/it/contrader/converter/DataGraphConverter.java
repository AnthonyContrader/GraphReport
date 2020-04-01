package it.contrader.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.contrader.dto.CategoriaDTO;
import it.contrader.dto.DataGraphDTO;
import it.contrader.model.Categoria;
import it.contrader.model.DataGraph;
import it.contrader.model.DataSet;
import it.contrader.model.Graph;

@Component
public class DataGraphConverter extends AbstractConverter<DataGraph, DataGraphDTO> {
	
	@Autowired
	DataSetConverter dsconv;
	
	@Override
	public DataGraph toEntity(DataGraphDTO dto) {
		DataGraph model = new DataGraph();
		model.setDataSet(new DataSet(dto.getDataSetId(),null,null,null,null,null,null));
		model.setGraph(new Graph(dto.getGraphId(),null,null,null,null,null,null,null));
		model.setAsse(dto.getAsse());
		return model;
		}
	@Override
	public DataGraphDTO toDTO(DataGraph model) {
		DataGraphDTO dto = new DataGraphDTO();
		dto.setId(model.getId());
		dto.setDataSetId(model.getDataSet().getId());
		dto.setDataSet(dsconv.toDTO(model.getDataSet()));
		dto.setGraphId(model.getGraph().getId());
		dto.setAsse(model.getAsse());
		return dto;
	}

}
