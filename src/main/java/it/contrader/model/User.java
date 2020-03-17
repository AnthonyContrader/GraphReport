package it.contrader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

	public enum Usertype {
		ADMIN, USER
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable=false)
	private String username;

	private String password;
	
	private Usertype usertype;
	
	@Column(unique = true)
	private String email;
	
	private String nome;
	
	private String cognome;
	
	private String citta;
	
	private String nazione;
}
