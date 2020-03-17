package it.contrader.dto;

import it.contrader.model.User.Usertype;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

	private Long id;

	private String username;

	private String password;

	private Usertype usertype;
	
	private String email;
	
	private String nome;
	
	private String cognome;
	
	private String citta;
	
	private String nazione;

}
