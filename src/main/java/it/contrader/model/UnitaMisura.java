package it.contrader.model;

public class UnitaMisura {
	
	//-------------------ATTRIBUTI-------------------
	private int id;
	private String nome;
	
	//-------------------COSTRUTORI-------------------
	public UnitaMisura() {
		
	}
	
	public UnitaMisura(String nome) {
		this.nome = nome;
	}
	
	public UnitaMisura(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	//-------------------GETTER E SETTER-------------------
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

	//-------------------equals E toString-------------------
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitaMisura other = (UnitaMisura) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "id\t" + id + "\t nome\t" + nome;
	}
	
}
