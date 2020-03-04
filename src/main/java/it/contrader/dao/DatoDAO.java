package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Dato;

public class DatoDAO {

	private final String QUERY_ALL = "SELECT * FROM dato";
	private final String QUERY_CREATE = "INSERT INTO dato (id_utente, id_area, id_tag, valore) VALUES (?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM dato WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE dato SET id_utente=?, id_area=?, id_tag=?, valore=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM dato WHERE id=?";

	public DatoDAO() {

	}

	public List<Dato> getAll() {
		List<Dato> usersList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Dato dato;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int idUtente = resultSet.getInt("id_utente");
				int idArea = resultSet.getInt("id_area");
				int idTag = resultSet.getInt("id_tag");
				float valore = resultSet.getFloat("valore");
				dato = new Dato(id, idUtente, idArea, idTag, valore);
				dato.setId(id);
				usersList.add(dato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}

	public boolean insert(Dato userToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setInt(1, userToInsert.getIdUtente());
			preparedStatement.setInt(2, userToInsert.getIdArea());
			preparedStatement.setInt(3, userToInsert.getIdTag());
			preparedStatement.setFloat(4, userToInsert.getValore());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public Dato read(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int idUtente, idArea, idTag;
			float valore;

			idUtente = resultSet.getInt("id_utente");
			idArea = resultSet.getInt("id_area");
			idTag = resultSet.getInt("id_tag");
			valore = resultSet.getFloat("valore");
			Dato dato = new Dato(idUtente, idArea, idTag, valore);
			dato.setId(resultSet.getInt("id"));

			return dato;
		} catch (SQLException e) {
			return null;
		}

	}

	public boolean update(Dato datoToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (datoToUpdate.getId() == 0)
			return false;

		Dato datoRead = read(datoToUpdate.getId());
		if (!datoRead.equals(datoToUpdate)) {
			try {
				// Fill the userToUpdate object
				if (datoToUpdate.getIdUtente() == null || datoToUpdate.getIdUtente().equals("")) {
					datoToUpdate.setIdUtente(datoRead.getIdUtente());
				}

				if (datoToUpdate.getIdArea() == null || datoToUpdate.getIdArea().equals("")) {
					datoToUpdate.setIdArea(datoRead.getIdArea());
				}

				if (datoToUpdate.getIdTag() == null || datoToUpdate.getIdTag().equals("")) {
					datoToUpdate.setIdTag(datoRead.getIdTag());
				}
				
				if (datoToUpdate.getValore() == null || datoToUpdate.getValore().equals("")) {
					datoToUpdate.setValore(datoRead.getValore());
				}

				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setInt(1, datoToUpdate.getIdUtente());
				preparedStatement.setInt(2, datoToUpdate.getIdArea());
				preparedStatement.setInt(3, datoToUpdate.getIdTag());
				preparedStatement.setFloat(4, datoToUpdate.getValore());
				preparedStatement.setInt(5, datoToUpdate.getId());
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
