package it.contrader.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.User;

/**
 * 
 * @author Vittorio
 *
 *Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class UserDAO implements DAO<User> {

	private final String QUERY_ALL = "SELECT * FROM user";
	private final String QUERY_CREATE = "INSERT INTO user (username, password, usertype, nome, cognome, email, citta, nazione) VALUES (?,?,?,?,?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM user WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE user SET username=?, password=?, usertype=?, nome=?, cognome=?, email=?, citta=?, nazione=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM user WHERE id=?";

	public UserDAO() {

	}

	public List<User> getAll() {
		List<User> usersList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			User user;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String usertype = resultSet.getString("usertype");
				String nome = resultSet.getString("nome");
				String cognome = resultSet.getString("cognome");
				String email = resultSet.getString("email");
				String citta = resultSet.getString("citta");
				String nazione = resultSet.getString("nazione");
				user = new User(username, password, usertype, nome, cognome, email, citta, nazione);
				user.setId(id);
				usersList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}

	public boolean insert(User userToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, userToInsert.getUsername());
			preparedStatement.setString(2, userToInsert.getPassword());
			preparedStatement.setString(3, userToInsert.getUsertype());
			preparedStatement.setString(4, userToInsert.getNome());
			preparedStatement.setString(5, userToInsert.getCognome());
			preparedStatement.setString(6, userToInsert.getEmail());
			preparedStatement.setString(7, userToInsert.getCitta());
			preparedStatement.setString(8, userToInsert.getNazione());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public User read(int userId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String username, password, usertype, nome, cognome, email, citta, nazione;

			username = resultSet.getString("username");
			password = resultSet.getString("password");
			usertype = resultSet.getString("usertype");
			nome = resultSet.getString("nome");
			cognome = resultSet.getString("cognome");
			email = resultSet.getString("email");
			citta = resultSet.getString("citta");
			nazione = resultSet.getString("nazione");
			
			User user = new User(username, password, usertype, nome, cognome, email, citta, nazione);
			user.setId(resultSet.getInt("id"));

			return user;
			
		} catch (SQLException e) {
			return null;
		}

	}

	public boolean update(User userToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (userToUpdate.getId() == 0)
			return false;

		User userRead = read(userToUpdate.getId());
		if (!userRead.equals(userToUpdate)) {
			try {
				// Fill the userToUpdate object
				if (userToUpdate.getUsername() == null || userToUpdate.getUsername().equals("")) {
					userToUpdate.setUsername(userRead.getUsername());
				}

				if (userToUpdate.getPassword() == null || userToUpdate.getPassword().equals("")) {
					userToUpdate.setPassword(userRead.getPassword());
				}

				if (userToUpdate.getUsertype() == null || userToUpdate.getUsertype().equals("")) {
					userToUpdate.setUsertype(userRead.getUsertype());
				}
				
				if (userToUpdate.getNome() == null || userToUpdate.getNome().equals("")) {
					userToUpdate.setNome(userRead.getNome());
				}
				if (userToUpdate.getCognome() == null || userToUpdate.getCognome().equals("")) {
					userToUpdate.setCognome(userRead.getCognome());
				}
				if (userToUpdate.getEmail() == null || userToUpdate.getEmail().equals("")) {
					userToUpdate.setEmail(userRead.getEmail());
				}
				if (userToUpdate.getCitta() == null || userToUpdate.getCitta().equals("")) {
					userToUpdate.setCitta(userRead.getCitta());
				}
				if (userToUpdate.getNazione() == null || userToUpdate.getNazione().equals("")) {
					userToUpdate.setNazione(userRead.getNazione());
				}

				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, userToUpdate.getUsername());
				preparedStatement.setString(2, userToUpdate.getPassword());
				preparedStatement.setString(3, userToUpdate.getUsertype());
				preparedStatement.setString(4, userToUpdate.getNome());
				preparedStatement.setString(5, userToUpdate.getCognome());
				preparedStatement.setString(6, userToUpdate.getEmail());
				preparedStatement.setString(7, userToUpdate.getCitta());
				preparedStatement.setString(8, userToUpdate.getNazione());
				preparedStatement.setInt(9, userToUpdate.getId());
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
