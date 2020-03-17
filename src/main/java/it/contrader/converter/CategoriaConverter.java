package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.CategoriaDTO;

import it.contrader.model.Categoria;

@Component
public class CategoriaConverter extends AbstractConverter<Categoria, CategoriaDTO> {
	
	@Override
	public Categoria toEntity(CategoriaDTO categoriaDTO) {
		Categoria categoria = null;
		if(categoriaDTO != null) {
			categoria = new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
		}
		return categoria;
		
		}
	@Override
	public CategoriaDTO toDTO(Categoria categoria) {
		CategoriaDTO categoriaDTO = null;
		if(categoria != null) {
			categoriaDTO = new CategoriaDTO(categoria.getId(), categoria.getNome());
		}
		return categoriaDTO;
	}

}
