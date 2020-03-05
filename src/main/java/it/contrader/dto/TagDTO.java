package it.contrader.dto;

public class TagDTO {
	private int id;
	private String nomeTag;
	
	public TagDTO() {
		
	}
	
	public TagDTO(String nomeTag) {
		this.nomeTag = nomeTag;
	}
	
	public TagDTO(int id, String nomeTag) {
		this.id = id;
		this.nomeTag = nomeTag;
	}

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

	@Override
	public String toString() {
		return id +"\t  " + nomeTag;
	}
	
}
