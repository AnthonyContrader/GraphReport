package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.DatoDTO;
import it.contrader.model.Dato;

public class DatoConverter   {

	/**
	 * Crea un oggetto di tipo DatoDTO e lo riempie con i campi del parametro user di tipo Dato.
	 */
	public DatoDTO toDTO(Dato dato) {
		DatoDTO datoDTO = new DatoDTO(dato.getId(), dato.getIdUtente(), dato.getIdArea(), dato.getIdTag(), dato.getValore());
		return datoDTO;
	}

	/**
	 * Crea un oggetto di tipo Dato e lo riempie con i campi del parametro dato di tipo DatoDTO.
	 */
	public Dato toEntity(DatoDTO datoDTO) {
		Dato dato = new Dato(datoDTO.getId(), datoDTO.getIdUtente(), datoDTO.getIdArea(), datoDTO.getIdTag(), datoDTO.getValore());
		return dato;
	}
	
	/**
	 * Metodo per convertire le liste di User.
	 */
	public List<DatoDTO> toDTOList(List<Dato> datoList) {
		//Crea una lista vuota.
		List<DatoDTO> datoDTOList = new ArrayList<DatoDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(Dato dato : datoList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			datoDTOList.add(toDTO(dato));
		}
		return datoDTOList;
	}

	
		
}
