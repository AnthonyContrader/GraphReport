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
	private String categoriaN;
	
	private Long unitaMisura;
	private String unitaMisuraN;
	
	private String valore;
	
	private String commento;

}
