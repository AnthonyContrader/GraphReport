package it.contrader.graphreport.service.impl;

import it.contrader.graphreport.service.MtMService;
import it.contrader.graphreport.domain.MtM;
import it.contrader.graphreport.repository.MtMRepository;
import it.contrader.graphreport.service.dto.MtMDTO;
import it.contrader.graphreport.service.mapper.MtMMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MtM.
 */
@Service
@Transactional
public class MtMServiceImpl implements MtMService {

    private final Logger log = LoggerFactory.getLogger(MtMServiceImpl.class);

    private final MtMRepository mtMRepository;

    private final MtMMapper mtMMapper;

    public MtMServiceImpl(MtMRepository mtMRepository, MtMMapper mtMMapper) {
        this.mtMRepository = mtMRepository;
        this.mtMMapper = mtMMapper;
    }

    /**
     * Save a mtM.
     *
     * @param mtMDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MtMDTO save(MtMDTO mtMDTO) {
        log.debug("Request to save MtM : {}", mtMDTO);
        MtM mtM = mtMMapper.toEntity(mtMDTO);
        mtM = mtMRepository.save(mtM);
        return mtMMapper.toDto(mtM);
    }

    /**
     * Get all the mtMS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MtMDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MtMS");
        return mtMRepository.findAll(pageable)
            .map(mtMMapper::toDto);
    }


    /**
     * Get one mtM by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MtMDTO> findOne(Long id) {
        log.debug("Request to get MtM : {}", id);
        return mtMRepository.findById(id)
            .map(mtMMapper::toDto);
    }

    /**
     * Delete the mtM by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MtM : {}", id);
        mtMRepository.deleteById(id);
    }
}
