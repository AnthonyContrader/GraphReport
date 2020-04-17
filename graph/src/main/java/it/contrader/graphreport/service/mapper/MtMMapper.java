package it.contrader.graphreport.service.mapper;

import it.contrader.graphreport.domain.MtM;
import it.contrader.graphreport.service.dto.MtMDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MtM and its DTO MtMDTO.
 */
@Mapper(componentModel = "spring", uses = {GraphMapper.class})
public interface MtMMapper extends EntityMapper<MtMDTO, MtM> {

    @Mapping(source = "graph.id", target = "graphId")
    @Mapping(source = "dataSet", target = "dataSet.id")
    MtMDTO toDto(MtM mtM);

    @Mapping(source = "graphId", target = "graph")
    @Mapping(source = "dataSet.id", target = "dataSet")
    MtM toEntity(MtMDTO mtMDTO);
    
    

    default MtM fromId(Long id) {
        if (id == null) {
            return null;
        }
        MtM mtM = new MtM();
        mtM.setId(id);
        return mtM;
    }
}
