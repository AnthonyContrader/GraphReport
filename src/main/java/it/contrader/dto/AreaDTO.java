package it.contrader.dto;

/**
 * 
 * @author Vittorio
 *
 *Il DTO è simile al Model ma può contenere meno attributi (ad esempio d dati sensibili
 *che non devono arrivare alla View). GLi oggetti vengono trasformati da oggetti del Model
 *a oggetti del DTO tramite i Converter (chiamati dai Service). 
 *Per la descrizione della classe far riferimento al Model "Area".
 */
public class AreaDTO {
	
	private int id;

	private String nome;

	
	public AreaDTO() {
		
	}

	public AreaDTO (String nome) {
		this.nome = nome;
	}
		

	public AreaDTO (int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

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

	@Override
	public String toString() {
		return  id + "\t"  + nome;
	}
}
