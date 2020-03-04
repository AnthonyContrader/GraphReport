package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.TagDTO;
import it.contrader.model.Tag;

public class TagConverter {

	public TagDTO toDTO(Tag tag) {
		TagDTO tagDTO = new TagDTO(tag.getId(), tag.getNomeTag());
		return tagDTO;
	}

	public Tag toEntity(TagDTO tagDTO) {
		Tag tag = new Tag(tagDTO.getId(), tagDTO.getNomeTag());
		return tag;
	}
	
	public List<TagDTO> toDTOList(List<Tag> tagList) {
		List<TagDTO> tagDTOList = new ArrayList<TagDTO>();
		
		for(Tag tag : tagList) {
			tagDTOList.add(toDTO(tag));
		}
		return tagDTOList;
	}
}
