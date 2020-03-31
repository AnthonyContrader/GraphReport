package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.GraphDTO;
import it.contrader.model.Graph;

@Component
public class GraphConverter extends AbstractConverter<Graph,GraphDTO> {

	@Override
	public Graph toEntity(GraphDTO dto) {
		Graph graph = new Graph(dto.getId(),dto.getTitolo(),dto.getFontStyle(),dto.getTipografico(),
		dto.getPosTitolo(),dto.getLegenda(),dto.getPareto(),null);
		return graph;
	}

	@Override
	public GraphDTO toDTO(Graph entity) {
		GraphDTO graphDTO = new GraphDTO(entity.getId(),entity.getTitolo(),entity.getFontStyle(),entity.getTipografico(),
				entity.getPosTitolo(),entity.getLegenda(),entity.getPareto());
		return graphDTO;
	}

}
