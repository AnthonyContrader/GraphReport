package it.contrader.graphreport.service.mapper;

import it.contrader.graphreport.domain.*;
import it.contrader.graphreport.service.dto.GraphDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Graph and its DTO GraphDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GraphMapper extends EntityMapper<GraphDTO, Graph> {


    @Mapping(target = "mtMS", ignore = true)
    Graph toEntity(GraphDTO graphDTO);

    default Graph fromId(Long id) {
        if (id == null) {
            return null;
        }
        Graph graph = new Graph();
        graph.setId(id);
        return graph;
    }
}
