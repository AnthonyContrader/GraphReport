package it.contrader.model;
/**
 * Per dettagli vedi guida sez 4 Model
 */
public class User {

	/**
	 * Qui sotto definisco gli attributi di User. 
	 */
	private Integer id;
	
	private String username;
	
	private String password;
	
	private String usertype;
	
	private String nome;
	
	private String cognome;
	
	private String email;

	private String citta;

	private String nazione;
	
	/**
	 * Definisco i due costruttori, uno vuoto e uno con tre parametri per costrire oggetti di tipo User
	 */
	public User() {
		id=null;
	}
	
	public User(int id, String username, String password, String usertype) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}
	
	public User(String username, String password, String usertype) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}
	
	public User(String username, String password, String usertype, String nome, String cognome, String email, String citta, String nazione) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.citta = citta;
		this.nazione = nazione;
	}

	public User(Integer id, String username, String password, String usertype, String nome, String cognome, String email, String citta, String nazione) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.citta = citta;
		this.nazione = nazione;
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

	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsertype() {
		return this.usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
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

	//Trasforma un oggetto in una stringa
	@Override
	public String toString() {
		return  id + "\t"  + username +"\t\t" +   password + "\t\t" + usertype + nome + "\t\t" + cognome + "\t\t" + email + "\t\t" + citta + "\t\t" + nazione;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (usertype == null) {
			if (other.usertype != null)
				return false;
		} else if (!usertype.equals(other.usertype))
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
		return true;
	}
}
