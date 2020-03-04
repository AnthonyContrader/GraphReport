package it.contrader.service;

import java.util.List;
import it.contrader.converter.TagConverter;
import it.contrader.dao.TagDAO;
import it.contrader.dto.TagDTO;

public class TagService {
	
	private TagDAO tagDAO;
	private TagConverter tagConverter;
	
	public TagService(){
		this.tagDAO = new TagDAO();
		this.tagConverter = new TagConverter();
	}
	

	public List<TagDTO> getAll() {
		return tagConverter.toDTOList(tagDAO.getAll());
	}


	public TagDTO read(int id) {
		return tagConverter.toDTO(tagDAO.read(id));
	}


	public boolean insert(TagDTO dto) {
		return tagDAO.insert(tagConverter.toEntity(dto));
	}


	public boolean update(TagDTO dto) {
		return tagDAO.update(tagConverter.toEntity(dto));
	}


	public boolean delete(int id) {
		return tagDAO.delete(id);
	}
	
	public Integer getId(String nome) {
		return tagDAO.getId(nome);
	}

}
