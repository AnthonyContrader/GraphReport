package it.contrader.model;

public class DataSet {

	/**
	 * Qui sotto definisco gli attributi di Dato. 
	 */
	private Integer id;

	private Integer idUtente;
	
	private Integer idCategoria;
	
	private Integer idUnitaMisura;
	
	private String valore;

	/**
	 * Definisco i due costruttori, uno vuoto e uno con tre parametri per costrire oggetti di tipo Dato
	 */
	public DataSet() {
		this.id=null;
	}

	public DataSet (int idutente, int idcat, int idunit, String valore) {
		this.idUtente = idutente;
		this.idCategoria = idcat;
		this.idUnitaMisura = idunit;
		this.valore = valore;
	}

	public DataSet (Integer id, int idutente, int idcat, int idunit, String valore) {
		this.id = id;
		this.idUtente = idutente;
		this.idCategoria = idcat;
		this.idUnitaMisura = idunit;
		this.valore = valore;
	}

	/**
	 * Getter e Setter: servono alle altre classi a recuperare e modificare gli attributi di Dato
	 */
	public Integer getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdUser() {
		return this.idUtente;
	}

	public void setIdUser(int idutente) {
		this.idUtente = idutente;
	}


	public Integer getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idcat) {
		this.idCategoria = idcat;
	}

	public void setIdUnitaMisura(int idunit) {
		this.idUnitaMisura = idunit;
	}

	public Integer getIdUnitaMisura() {
		return this.idUnitaMisura;
	}
	
	public void setValore(String valore) {
		this.valore = valore;
	}

	public String getValore() {
		return this.valore;
	}

	//Trasforma un oggetto in una stringa
	@Override
	public String toString() {
		return "id: " + id + "\t idUtente: "  + idUtente +"\t\t idArea: " +   idCategoria + "\t\t idTag: " + idUnitaMisura + "\t\t Valore: " + valore;
	}

	//Metodo per il confronto degli oggetti
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataSet other = (DataSet) obj;
		if (id != other.id)
			return false;
		if (idUtente == null) {
			if (other.idUtente != null)
				return false;
		} else if (idCategoria != other.idCategoria)
			return false;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (idCategoria != other.idCategoria)
			return false;
		if (idUnitaMisura == null) {
			if (other.idUnitaMisura != null)
				return false;
		} else if (idUnitaMisura != other.idUnitaMisura)
			return false;
		if (valore == null) {
			if (other.valore != null)
				return false;
		} else if (valore != other.valore)
			return false;
		return true;
	}
}
