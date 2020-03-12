package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.DataSetDTO;
import it.contrader.model.DataSet;

public class DataSetConverter   {

	/**
	 * Crea un oggetto di tipo DatoDTO e lo riempie con i campi del parametro user di tipo Dato.
	 */
	public DataSetDTO toDTO(DataSet dato,String cat,String unit) {
		
		DataSetDTO datoDTO = new DataSetDTO(dato.getId(), dato.getIdUser(), cat, unit, dato.getValore());
		return datoDTO;
	}

	/**
	 * Crea un oggetto di tipo Dato e lo riempie con i campi del parametro dato di tipo DatoDTO.
	 */
	public DataSet toEntity(DataSetDTO datoDTO,Integer cat, Integer unit) {
		DataSet dato = new DataSet(datoDTO.getId(),datoDTO.getUser(), cat, unit, datoDTO.getValore());
		return dato;
	}
	
	/**
	 * Metodo per convertire le liste di User.
	 */
	public List<DataSetDTO> toDTOList(List<DataSet> datoList, List<String> categoriaList, List<String> unitamisuraList) {
		
		//Crea una lista vuota.
		List<DataSetDTO> datoDTOList = new ArrayList<>();
		
		int index =0;
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(DataSet dato : datoList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			
			datoDTOList.add(toDTO(dato,categoriaList.get(index).toString(),unitamisuraList.get(index).toString()));
			index++;
		}
		return datoDTOList;
	}
	

			
}
