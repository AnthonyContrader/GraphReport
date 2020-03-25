package it.contrader.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
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
