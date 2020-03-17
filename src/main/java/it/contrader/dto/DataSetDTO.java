package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSetDTO {

	
	private Long id;

	private Long utente;
	
	private Long categoria;
	
	private Long unitaMisura;
	
	private String valore;

}
