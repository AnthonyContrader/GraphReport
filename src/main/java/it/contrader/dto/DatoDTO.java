package it.contrader.dto;

public class DatoDTO {

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
	public DatoDTO() {
		
	}

	public DatoDTO (int idutente, int idarea, int idtag, float valore) {
		this.idUtente = idutente;
		this.idArea = idarea;
		this.idTag = idtag;
		this.valore = valore;
	}

	public DatoDTO (int id, int idutente, int idarea, int idtag, float valore) {
		this.id = id;
		this.idUtente = idutente;
		this.idArea = idarea;
		this.idTag = idtag;
		this.valore = valore;
	}

	/**
	 * Getter e Setter: servono alle altre classi a recuperare e modificare gli attributi di Dato
	 */
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getIdUtente() {
		return this.idUtente;
	}

	public void setIdUtente(int idutente) {
		this.idUtente = idutente;
	}


	public int getIdArea() {
		return this.idArea;
	}

	public void setIdArea(int idarea) {
		this.idArea = idarea;
	}

	public void setIdTag(int idtag) {
		this.idTag = idtag;
	}

	public int getIdTag() {
		return this.idTag;
	}
	
	public void setValore(float valore) {
		this.valore = valore;
	}

	public float getValore() {
		return this.valore;
	}

	//Trasforma un oggetto in una stringa
	@Override
	public String toString() {
		return "id: " + id + "\t idUtente: "  + idUtente +"\t\t idArea: " +   idArea + "\t\t idTag: " + idTag + "\t\t Valore: " + valore;
	}

	
}
