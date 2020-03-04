package it.contrader.model;
/**
 * Per dettagli vedi guida sez 4 Model
 */
public class Area {

	/**
	 * Qui sotto definisco gli attributi di Area. 
	 */
	private int id;

	private String nome;

	/**
	 * Definisco i due costruttori
	 */
	public Area() {
		
	}

	public Area (String nome) {
		this.nome = nome;
	}

	public Area (int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	/**
	 * Getter e Setter: servono alle altre classi a recuperare e modificare gli attributi di Area
	 */
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	//Trasforma un oggetto in una stringa
	@Override
	public String toString() {
		return  id + "\t"  + nome;
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
		Area other = (Area) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
