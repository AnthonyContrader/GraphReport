package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.GraphDTO;
import it.contrader.model.Graph;

@Component
public class GraphConverter extends AbstractConverter<Graph,GraphDTO> {

	@Override
	public Graph toEntity(GraphDTO dto) {
		Graph graph = new Graph(dto.getId(),dto.getTitolo(),dto.getTipografico(),dto.getTema(),
				dto.getPosTitolo(),dto.getLegenda(),dto.getZoom(),dto.getPareto(),dto.getDataset());
		
		return graph;
	}

	@Override
	public GraphDTO toDTO(Graph entity) {
		GraphDTO graphDTO = new GraphDTO(entity.getId(),entity.getTitolo(),entity.getTipografico(),entity.getTema(),
				entity.getPosTitolo(),entity.getLegenda(),entity.getZoom(),entity.getPareto(),entity.getDataset());
		return graphDTO;
	}

}
