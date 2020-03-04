package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.AreaDTO;
import it.contrader.model.Area;

/**
 *  
 *
 */
public class AreaConverter   {
	
	/**
	 * Crea un oggetto di tipo UserDTO e lo riempie con i campi del parametro user di tipo Area.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public AreaDTO toDTO(Area area) {
		AreaDTO areaDTO = new AreaDTO(area.getId(), area.getNome());
		return areaDTO;
	}

	/**
	 * Crea un oggetto di tipo Area e lo riempie con i campi del parametro area di tipo AreaDTO.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public Area toEntity(AreaDTO areaDTO) {
		Area area = new Area(areaDTO.getId(), areaDTO.getNome());
		return area;
	}
	
	/**
	 * Metodo per convertire le liste di Area.
	 */
	public List<AreaDTO> toDTOList(List<Area> areaList) {
		//Crea una lista vuota.
		List<AreaDTO> areaDTOList = new ArrayList<AreaDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(Area area : areaList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			areaDTOList.add(toDTO(area));
		}
		return areaDTOList;
	}

	
	
}
