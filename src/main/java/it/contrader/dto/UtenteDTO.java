package it.contrader.dto;

/**
 * 
 * @author Vittorio
 *
 *Il DTO è simile al Model ma può contenere meno attributi (ad esempio d dati sensibili
 *che non devono arrivare alla View). GLi oggetti vengono trasformati da oggetti del Model
 *a oggetti del DTO tramite i Converter (chiamati dai Service). 
 *Per la descrizione della classe far riferimento al Model "User".
 */
public class UtenteDTO {

	private int id;

	private String nome;
	
	private String cognome;
	
	private String email;

	private String citta;

	private String nazione;
	
	private int iduser;

	
	public UtenteDTO() {
		
	}

	public UtenteDTO (String nome, String cognome, String email, String citta, String nazione, int iduser) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.citta = citta;
		this.nazione = nazione;
		this.iduser= iduser;
	}

	public UtenteDTO (int id, String nome, String cognome, String email, String citta, String nazione, int iduser) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.citta = citta;
		this.nazione = nazione;
		this.iduser=iduser;
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

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCitta() {
		return this.citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String getNazione() {
		return this.nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
	public int getIdUser() {
		return this.iduser;
	}
	public void setIdUser(int iduser) {
		this.iduser = iduser;
	}
	@Override
	public String toString() {
		return  id + "\t"  + nome + "\t\t" + cognome + "\t\t" + email + "\t\t" + citta + "\t\t" + nazione + "\t\t" + iduser;
	}
}
