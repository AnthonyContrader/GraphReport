package it.contrader.graphreport.service.impl;

import it.contrader.graphreport.service.UnitamisuraService;
import it.contrader.graphreport.domain.Unitamisura;
import it.contrader.graphreport.repository.UnitamisuraRepository;
import it.contrader.graphreport.service.dto.UnitamisuraDTO;
import it.contrader.graphreport.service.mapper.UnitamisuraMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Unitamisura.
 */
@Service
@Transactional
public class UnitamisuraServiceImpl implements UnitamisuraService {

    private final Logger log = LoggerFactory.getLogger(UnitamisuraServiceImpl.class);

    private final UnitamisuraRepository unitamisuraRepository;

    private final UnitamisuraMapper unitamisuraMapper;

    public UnitamisuraServiceImpl(UnitamisuraRepository unitamisuraRepository, UnitamisuraMapper unitamisuraMapper) {
        this.unitamisuraRepository = unitamisuraRepository;
        this.unitamisuraMapper = unitamisuraMapper;
    }

    /**
     * Save a unitamisura.
     *
     * @param unitamisuraDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UnitamisuraDTO save(UnitamisuraDTO unitamisuraDTO) {
        log.debug("Request to save Unitamisura : {}", unitamisuraDTO);
        Unitamisura unitamisura = unitamisuraMapper.toEntity(unitamisuraDTO);
        unitamisura = unitamisuraRepository.save(unitamisura);
        return unitamisuraMapper.toDto(unitamisura);
    }

    /**
     * Get all the unitamisuras.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UnitamisuraDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Unitamisuras");
        return unitamisuraRepository.findAll(pageable)
            .map(unitamisuraMapper::toDto);
    }


    /**
     * Get one unitamisura by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UnitamisuraDTO> findOne(Long id) {
        log.debug("Request to get Unitamisura : {}", id);
        return unitamisuraRepository.findById(id)
            .map(unitamisuraMapper::toDto);
    }

    /**
     * Delete the unitamisura by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Unitamisura : {}", id);
        unitamisuraRepository.deleteById(id);
    }
}
