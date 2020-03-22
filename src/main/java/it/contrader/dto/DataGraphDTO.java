package it.contrader.dto;

import it.contrader.model.DataSet;
import it.contrader.model.Graph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataGraphDTO {
	
	private Long id;
	
	private Long dataSetId;
	private DataSet dataSet;
	
	private Long graphId;
	private Graph graph;
	
}
