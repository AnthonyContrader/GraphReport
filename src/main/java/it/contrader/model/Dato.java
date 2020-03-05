package it.contrader.model;

public class Dato {

	/**
	 * Qui sotto definisco gli attributi di Dato. 
	 */
	private Integer id;

	private Integer idUtente;
	
	private Integer idArea;
	
	private Integer idTag;
	
	private Float valore;

	/**
	 * Definisco i due costruttori, uno vuoto e uno con tre parametri per costrire oggetti di tipo Dato
	 */
	public Dato() {
		
	}

	public Dato (int idutente, int idarea, int idtag, float valore) {
		this.idUtente = idutente;
		this.idArea = idarea;
		this.idTag = idtag;
		this.valore = valore;
	}

	public Dato (Integer id, int idutente, int idarea, int idtag, float valore) {
		this.id = id;
		this.idUtente = idutente;
		this.idArea = idarea;
		this.idTag = idtag;
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

	public Integer getIdUtente() {
		return this.idUtente;
	}

	public void setIdUtente(int idutente) {
		this.idUtente = idutente;
	}


	public Integer getIdArea() {
		return this.idArea;
	}

	public void setIdArea(int idarea) {
		this.idArea = idarea;
	}

	public void setIdTag(int idtag) {
		this.idTag = idtag;
	}

	public Integer getIdTag() {
		return this.idTag;
	}
	
	public void setValore(float valore) {
		this.valore = valore;
	}

	public Float getValore() {
		return this.valore;
	}

	//Trasforma un oggetto in una stringa
	@Override
	public String toString() {
		return "id: " + id + "\t idUtente: "  + idUtente +"\t\t idArea: " +   idArea + "\t\t idTag: " + idTag + "\t\t Valore: " + valore;
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
		Dato other = (Dato) obj;
		if (id != other.id)
			return false;
		if (idUtente == null) {
			if (other.idUtente != null)
				return false;
		} else if (idArea != other.idArea)
			return false;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (idArea != other.idArea)
			return false;
		if (idTag == null) {
			if (other.idTag != null)
				return false;
		} else if (idTag != other.idTag)
			return false;
		if (valore == null) {
			if (other.valore != null)
				return false;
		} else if (valore != other.valore)
			return false;
		return true;
	}
}
