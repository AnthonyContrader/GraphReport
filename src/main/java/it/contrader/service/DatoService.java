package it.contrader.service;

import java.util.ArrayList;
import java.util.List;

import it.contrader.controller.Request;
import it.contrader.converter.DatoConverter;
import it.contrader.dao.DatoDAO;
import it.contrader.dto.DatoDTO;
import it.contrader.model.Dato;

/**
 *Grazie all'ereditarietà mi basta specificare i tipi di questa classe per
 *ereditare i metodi della clase AbstractService. Pertanto la classe risulta meno complicata
 *da scrivere, facendoci risparmiare tempo e fatica!
 */
public class DatoService {
	
	private DatoDAO datoDAO;
	private DatoConverter datoConverter;
	
	//Istanzio DAO  e Converter specifici.
	public DatoService(){
		this.datoDAO = new DatoDAO();
		this.datoConverter = new DatoConverter();
	}
	

	public List<DatoDTO> getAllByUtente(int idUtente) {
		// Ottiene una lista di entità e le restituisce convertendole in DTO
		Request request = new Request();
		AreaService areaService = new AreaService();
		TagService tagService = new TagService();
		
		List<Dato> listaDato = datoDAO.getAllByUtente(idUtente);
		
		List<String> area = new ArrayList<>(), tag = new ArrayList<>();
		
		for(Dato dato : listaDato) {
			area.add(areaService.read(dato.getIdArea()).getNome());
			tag.add(tagService.read(dato.getIdTag()).getNomeTag());
		}

		request.put("model", listaDato);
		request.put("area", area);
		request.put("tag", tag);
		
		return datoConverter.toDTOList(request);
	}

	public List<DatoDTO> getAll() {
		// Ottiene una lista di entità e le restituisce convertendole in DTO
		Request request = new Request();
		AreaService areaService = new AreaService();
		TagService tagService = new TagService();
		
		List<Dato> listaDato = datoDAO.getAll();
		
		List<String> area = new ArrayList<>(), tag = new ArrayList<>();
		
		for(Dato dato : listaDato) {
			area.add(areaService.read(dato.getIdArea()).getNome());
			tag.add(tagService.read(dato.getIdTag()).getNomeTag());
		}

		request.put("model", listaDato);
		request.put("area", area);
		request.put("tag", tag);
		
		return datoConverter.toDTOList(request);
	}


	public DatoDTO read(int id) {
		// Ottiene un'entità e la restituisce convertendola in DTO
		AreaService areaService = new AreaService();
		TagService tagService = new TagService();
		Dato dato = datoDAO.read(id);
		return datoConverter.toDTO(dato,areaService.read(dato.getIdArea()).getNome(),tagService.read(dato.getIdTag()).getNomeTag());
	}


	public boolean insert(DatoDTO dto) {
		// Converte un DTO in entità e lo passa al DAO per l'inserimento
		AreaService areaService = new AreaService();
		TagService tagService = new TagService();
		return datoDAO.insert(datoConverter.toEntity(dto,tagService.getId(dto.getTag()),areaService.getId(dto.getArea())));
	}


	public boolean update(DatoDTO dto) {
		// Converte un DTO in entità e lo passa allo DAO per la modifica
		AreaService areaService = new AreaService();
		TagService tagService = new TagService();
		return datoDAO.update(datoConverter.toEntity(dto,tagService.getId(dto.getTag()),areaService.getId(dto.getArea())));
	}


	public boolean delete(int id) {
		// Questo mtodo chiama direttamente il DAO
		return datoDAO.delete(id);
	}
	

}
