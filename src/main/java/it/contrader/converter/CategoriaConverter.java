package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.CategoriaDTO;

import it.contrader.model.Categoria;

/**
 * Questa classe implementa i metodi di conversione dell'entità User.
 *  
 * @author Vittorio Valent & Girolamo Murdaca
 * 
 *@see AbstractConverter
 *@see Converter
 */
@Component
public class CategoriaConverter extends AbstractConverter<Categoria,CategoriaDTO> {

	@Override
	public Categoria toEntity(CategoriaDTO categoriaDTO) {
		Categoria categoria = null;
		if (categoriaDTO != null) {
			categoria = new Categoria(categoriaDTO.getId(),categoriaDTO.getNome());			
		}
		return categoria;
	}

	@Override
	public CategoriaDTO toDTO(Categoria categoria) {
		CategoriaDTO categoriaDTO = null;
		if (categoria != null) {
			categoriaDTO = new CategoriaDTO(categoria.getId(),categoria.getNome());
			
		}
		return categoriaDTO;
	}

	

}