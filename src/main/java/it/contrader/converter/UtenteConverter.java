package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.UtenteDTO;
import it.contrader.model.Utente;

/**
 * 
 * @author Vittorio, De Santis 
 *
 */
public class UtenteConverter   {
	
	/**
	 * Crea un oggetto di tipo UserDTO e lo riempie con i campi del parametro user di tipo User.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public UtenteDTO toDTO(Utente utente) {
		UtenteDTO utenteDTO = new UtenteDTO(utente.getId(), utente.getNome(), utente.getCognome(), utente.getEmail(),utente.getCitta(),utente.getNazione(),utente.getIdUser());
		return utenteDTO;
	}

	/**
	 * Crea un oggetto di tipo User e lo riempie con i campi del parametro user di tipo UserDTO.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public Utente toEntity(UtenteDTO utenteDTO) {
		Utente utente = new Utente(utenteDTO.getId(), utenteDTO.getNome(), utenteDTO.getCognome(), utenteDTO.getEmail(),utenteDTO.getCitta(),utenteDTO.getNazione(),utenteDTO.getIdUser());
		return utente;
	}
	
	/**
	 * Metodo per convertire le liste di User.
	 */
	public List<UtenteDTO> toDTOList(List<Utente> utenteList) {
		//Crea una lista vuota.
		List<UtenteDTO> utenteDTOList = new ArrayList<UtenteDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(Utente utente : utenteList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			utenteDTOList.add(toDTO(utente));
		}
		return utenteDTOList;
	}

	
	
}
