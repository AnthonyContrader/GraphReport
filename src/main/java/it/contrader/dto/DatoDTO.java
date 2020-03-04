package it.contrader.dto;

public class DatoDTO {

	/**
	 * Qui sotto definisco gli attributi di Dato. 
	 */
	private Integer id;

	private Integer idUtente;
	
	private String area;
	
	private String tag;
	
	private Float valore;

	/**
	 * Definisco i due costruttori, uno vuoto e uno con tre parametri per costrire oggetti di tipo Dato
	 */
	public DatoDTO() {
		
	}

	public DatoDTO (int idutente, String area, String tag, float valore) {
		this.idUtente = idutente;
		this.area = area;
		this.tag = tag;
		this.valore = valore;
	}

	public DatoDTO (int id, Integer idutente, String area, String tag, float valore) {
		this.id = id;
		this.idUtente = idutente;
		this.area = area;
		this.tag = tag;
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

	public Integer getUtente() {
		return this.idUtente;
	}

	public void setUtente(Integer utente) {
		this.idUtente = utente;
	}


	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return this.tag;
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
		return "id: " + id + "\t idUtente: "  + idUtente +"\t\t idArea: " +   area + "\t\t idTag: " + tag + "\t\t Valore: " + valore;
	}

	
}
