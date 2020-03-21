package it.contrader.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="USER")
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
	
	private String nome;
	
	private String cognome;
	
	@Column(unique = true)
	private String email;
	
	private String citta;
	
	private String nazione;
	
	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
	private List<DataSet> dataset;
}
