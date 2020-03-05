package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Utente;

/**
 * 
 * @author Vittorio
 *
 *Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class UtenteDAO {

	private final String QUERY_ALL = "SELECT * FROM utente";
	private final String QUERY_CREATE = "INSERT INTO utente (nome, cognome, email, citta, nazione, iduser) VALUES (?,?,?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM utente WHERE iduser=?";
	private final String QUERY_UPDATE = "UPDATE utente SET nome=?, cognome=?, email=?, citta=?, nazione=?, iduser=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM utente WHERE id=?";

	public UtenteDAO() {

	}

	public List<Utente> getAll() {
		List<Utente> utentiList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Utente utente;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				String cognome = resultSet.getString("cognome");
				String email = resultSet.getString("email");
				String citta = resultSet.getString("citta");
				String nazione = resultSet.getString("nazione");
				int iduser = resultSet.getInt("iduser");

				utente = new Utente(nome, cognome, email, citta, nazione,iduser);
				utente.setId(id);
				utentiList.add(utente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utentiList;
	}

	public boolean insert(Utente utenteToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, utenteToInsert.getNome());
			preparedStatement.setString(2, utenteToInsert.getCognome());
			preparedStatement.setString(3, utenteToInsert.getEmail());
			preparedStatement.setString(4, utenteToInsert.getCitta());
			preparedStatement.setString(5, utenteToInsert.getNazione());
			preparedStatement.setInt(6, utenteToInsert.getIdUser());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public Utente read(int utenteId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, utenteId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String nome, cognome, email, citta, nazione;
			int iduser;

			nome = resultSet.getString("nome");
			cognome = resultSet.getString("cognome");
			email = resultSet.getString("email");
			citta = resultSet.getString("citta");
			nazione = resultSet.getString("nazione");
			iduser = resultSet.getInt("iduser");
			Utente utente = new Utente(nome, cognome, email, citta, nazione, iduser);
			utente.setId(resultSet.getInt("id"));

			return utente;
		} catch (SQLException e) {
			return null;
		}

	}

	public boolean update(Utente utenteToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (utenteToUpdate.getId() == 0)
			return false;
		if (utenteToUpdate.getIdUser() == 0)
			return false;
		

		Utente utenteRead = read(utenteToUpdate.getId());
		if (!utenteRead.equals(utenteToUpdate)) {
			try {
				// Fill the utenteToUpdate object
				if (utenteToUpdate.getNome() == null || utenteToUpdate.getNome().equals("")) {
					utenteToUpdate.setNome(utenteRead.getNome());
				}
				if (utenteToUpdate.getCognome() == null || utenteToUpdate.getCognome().equals("")) {
					utenteToUpdate.setCognome(utenteRead.getCognome());
				}
				if (utenteToUpdate.getEmail() == null || utenteToUpdate.getEmail().equals("")) {
					utenteToUpdate.setEmail(utenteRead.getEmail());
				}
				if (utenteToUpdate.getCitta() == null || utenteToUpdate.getCitta().equals("")) {
					utenteToUpdate.setCitta(utenteRead.getCitta());
				}
				if (utenteToUpdate.getNazione() == null || utenteToUpdate.getNazione().equals("")) {
					utenteToUpdate.setNazione(utenteRead.getNazione());
				}

				// Update the utente
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, utenteToUpdate.getNome());
				preparedStatement.setString(2, utenteToUpdate.getCognome());
				preparedStatement.setString(3, utenteToUpdate.getEmail());
				preparedStatement.setString(4, utenteToUpdate.getCitta());
				preparedStatement.setString(5, utenteToUpdate.getNazione());
				preparedStatement.setInt(6, utenteToUpdate.getId());
				int a = preparedStatement.executeUpdate();
				if (a > 0)
					return true;
				else
					return false;

			} catch (SQLException e) {
				return false;
			}
		}

		return false;

	}

	public boolean delete(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, id);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
		}
		return false;
	}


}
