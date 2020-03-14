package it.contrader.dto;

public class DataSetDTO {

	/**
	 * Qui sotto definisco gli attributi di Dato. 
	 */
	private Integer id;

	private Integer idUtente;
	
	private String categoria;
	
	private String unitamisura;
	
	private String valore;

	/**
	 * Definisco i due costruttori, uno vuoto e uno con tre parametri per costrire oggetti di tipo Dato
	 */
	public DataSetDTO() {
		
	}

	public DataSetDTO (int idutente, String categ, String unit, String valore) {
		this.idUtente = idutente;
		this.categoria = categ;
		this.unitamisura = unit;
		this.valore = valore;
		this.id=null;
	}

	public DataSetDTO (int id, Integer idutente, String categ, String unit, String valore) {
		this.id = id;
		this.idUtente = idutente;
		this.categoria = categ;
		this.unitamisura = unit;
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

	public Integer getUser() {
		return this.idUtente;
	}

	public void setUser(Integer utente) {
		this.idUtente = utente;
	}


	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categ) {
		this.categoria = categ;
	}

	public void setUnitaMisura(String unit) {
		this.unitamisura = unit;
	}

	public String getUnitaMisura() {
		return this.unitamisura;
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
		return "id: " + id +"\t\t idUtente: " + idUtente +"\t\t categ: " +   categoria + "\t\t unit: " + unitamisura + "\t\t Valore: " + valore;
	}

	
}
