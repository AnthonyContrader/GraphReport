package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.CategoriaDTO;
import it.contrader.model.Categoria;


/**
 *  
 *
 */
public class CategoriaConverter implements Converter<Categoria, CategoriaDTO> {  
	
	/**
	 * Crea un oggetto di tipo UserDTO e lo riempie con i campi del parametro user di tipo Area.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public CategoriaDTO toDTO(Categoria categoria) {
		CategoriaDTO categoriaDTO = new CategoriaDTO(categoria.getId(), categoria.getNome());
		return categoriaDTO;
	}

	/**
	 * Crea un oggetto di tipo Categoria e lo riempie con i campi del parametro categoria di tipo CategoriaDTO.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public Categoria toEntity(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
		return categoria;
	}
	
	/**
	 * Metodo per convertire le liste delle Categorie.
	 */
	public List<CategoriaDTO> toDTOList(List<Categoria> categoriaList) {
		//Crea una lista vuota.
		List<CategoriaDTO> categoriaDTOList = new ArrayList<CategoriaDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(Categoria categoria : categoriaList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			categoriaDTOList.add(toDTO(categoria));
		}
		return categoriaDTOList;
	}

	
	
}
