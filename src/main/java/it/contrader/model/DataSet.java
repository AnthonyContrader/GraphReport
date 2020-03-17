package it.contrader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class DataSet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy="User")
	private Long utente;
	
	@Column(nullable=false)
	private Integer categoria;
	
	@Column(nullable=false)
	private Integer unitaMisura;
	
	private String valore;

}
