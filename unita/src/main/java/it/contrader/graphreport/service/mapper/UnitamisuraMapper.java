package it.contrader.graphreport.service.mapper;

import it.contrader.graphreport.domain.*;
import it.contrader.graphreport.service.dto.UnitamisuraDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Unitamisura and its DTO UnitamisuraDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoriaMapper.class})
public interface UnitamisuraMapper extends EntityMapper<UnitamisuraDTO, Unitamisura> {

    @Mapping(source = "unicat.id", target = "unicatId")
    UnitamisuraDTO toDto(Unitamisura unitamisura);

    @Mapping(source = "unicatId", target = "unicat")
    Unitamisura toEntity(UnitamisuraDTO unitamisuraDTO);

    default Unitamisura fromId(Long id) {
        if (id == null) {
            return null;
        }
        Unitamisura unitamisura = new Unitamisura();
        unitamisura.setId(id);
        return unitamisura;
    }
}
