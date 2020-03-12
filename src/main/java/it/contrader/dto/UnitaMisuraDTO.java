package it.contrader.dto;

public class UnitaMisuraDTO {
	private int id;
	private String nome;
	
	public UnitaMisuraDTO() {
		
	}
	
	public UnitaMisuraDTO(String nome) {
		this.nome = nome;
	}
	
	public UnitaMisuraDTO(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return id +"\t  " + nome;
	}
	
}
