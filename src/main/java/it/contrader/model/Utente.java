package it.contrader.model;
/**
 * Per dettagli vedi guida sez 4 Model
 */
public class Utente {

	/**
	 * Qui sotto definisco gli attributi di User. 
	 */
	private Integer id;

	private String nome;
	
	private String cognome;
	
	private String email;

	private String citta;

	private String nazione;
	
	private int iduser;

	/**
	 * Definisco i due costruttori, uno vuoto e uno con sei parametri per costrire oggetti di tipo User
	 */
	public Utente() {
		
	}

	public Utente (String nome, String cognome, String email, String citta, String nazione, int iduser) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.citta = citta;
		this.nazione = nazione;
		this.iduser = iduser;
	}

	public Utente (Integer id, String nome, String cognome, String email, String citta, String nazione, int iduser) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.citta = citta;
		this.nazione = nazione;
		this.iduser = iduser;
	}

	/**
	 * Getter e Setter: servono alle altre classi a recuperare e modificare gli attributi di User
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

	
	//Trasforma un oggetto in una stringa
	@Override
	public String toString() {
		return  id + "\t"  + nome + "\t\t" + cognome + "\t\t" + email + "\t\t" + citta + "\t\t" + nazione + "\t\t" + iduser;
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
		Utente other = (Utente) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (citta == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nazione == null) {
			if (other.nazione != null)
				return false;
		} else if (!nazione.equals(other.nazione))
			return false;
		if (iduser != other.iduser)
			return false;
		
		return true;
	}
}

