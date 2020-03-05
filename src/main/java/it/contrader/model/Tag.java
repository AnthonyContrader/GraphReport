package it.contrader.model;

public class Tag {
	
	//-------------------ATTRIBUTI-------------------
	private int id;
	private String nomeTag;
	
	//-------------------COSTRUTORI-------------------
	public Tag() {
		
	}
	
	public Tag(String nomeTag) {
		this.nomeTag = nomeTag;
	}
	
	public Tag(int id, String nomeTag) {
		this.id = id;
		this.nomeTag = nomeTag;
	}

	//-------------------GETTER E SETTER-------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeTag() {
		return nomeTag;
	}

	public void setNomeTag(String nomeTag) {
		this.nomeTag = nomeTag;
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
		Tag other = (Tag) obj;
		if (id != other.id)
			return false;
		if (nomeTag == null) {
			if (other.nomeTag != null)
				return false;
		} else if (!nomeTag.equals(other.nomeTag))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "id\t" + id + "\t nomeTag\t" + nomeTag;
	}
	
}
