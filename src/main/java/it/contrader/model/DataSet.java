package it.contrader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private Long id;

	@ManyToOne
	@JoinColumn( name = "UTENTE" , nullable=false)
	private User utente;
	
	@ManyToOne
	@JoinColumn(name="CATEGORIA", nullable=false)
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="UNITAMISURA", nullable=false)
	private UnitaMisura unitaMisura;
	
	private String valore;
	
	@Column(nullable=false)
	private String commento ="";

}
