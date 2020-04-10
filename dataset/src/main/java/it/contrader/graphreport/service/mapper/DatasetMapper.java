package it.contrader.graphreport.service.mapper;


import it.contrader.graphreport.domain.*;
import it.contrader.graphreport.service.dto.DatasetDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dataset} and its DTO {@link DatasetDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DatasetMapper extends EntityMapper<DatasetDTO, Dataset> {



    default Dataset fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dataset dataset = new Dataset();
        dataset.setId(id);
        return dataset;
    }
}
