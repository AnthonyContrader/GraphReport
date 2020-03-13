package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.DataSet;

public class DataSetDAO {

	private final String QUERY_ALL = "SELECT * FROM dataset ORDER BY id_user";
	private final String QUERY_ALL_BY_USER = "SELECT * FROM dataset WHERE id_user=? ORDER BY id_categoria";
	private final String QUERY_CREATE = "INSERT INTO dataset (id_user, id_categoria, id_unitamisura, valore) VALUES (?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM dataset WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE dataset SET id_user=?, id_categoria=?, id_unitamisura=?, valore=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM dataset WHERE id=?";

	public DataSetDAO() {

	}

	public List<DataSet> getAll() {
		List<DataSet> datiList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			DataSet dato;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int idUtente = resultSet.getInt("id_user");
				int idCat = resultSet.getInt("id_categoria");
				int idUnit = resultSet.getInt("id_unitamisura");
				String valore = resultSet.getString("valore");
				dato = new DataSet(id, idUtente, idCat, idUnit, valore);
				dato.setId(id);
				datiList.add(dato);
			}
		} catch (SQLException e) {
			
		}
		return datiList;
	}
	
	public List<DataSet> getAllByUtente(int user) {
		List<DataSet> datiList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement statement = (PreparedStatement)connection.prepareStatement(QUERY_ALL_BY_USER);
			statement.setInt(1,user);
			ResultSet resultSet = statement.executeQuery();
			DataSet dato;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int idUtente = resultSet.getInt("id_user");
				int idCat = resultSet.getInt("id_categoria");
				int idUnit = resultSet.getInt("id_unitamisura");
				String valore = resultSet.getString("valore");
				dato = new DataSet(id, idUtente, idCat, idUnit, valore);
				dato.setId(id);
				datiList.add(dato);
			}
		} catch (SQLException e) {
			
		}
		return datiList;
	}

	public boolean insert(DataSet userToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setInt(1, userToInsert.getIdUser());
			preparedStatement.setInt(2, userToInsert.getIdCategoria());
			preparedStatement.setInt(3, userToInsert.getIdUnitaMisura());
			preparedStatement.setString(4, userToInsert.getValore());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public DataSet read(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			int idUtente = resultSet.getInt("id_user");
			int idCat = resultSet.getInt("id_categoria");
			int idUnit = resultSet.getInt("id_unitamisura");
			String valore = resultSet.getString("valore");
			DataSet dato = new DataSet(idUtente, idCat, idUnit, valore);
			dato.setId(resultSet.getInt("id"));

			return dato;
		} catch (SQLException e) {
			return new DataSet();
		}

	}

	public boolean update(DataSet datoToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (datoToUpdate.getId() == 0)
			return false;

		DataSet datoRead = read(datoToUpdate.getId());
		if (!datoRead.equals(datoToUpdate)) {
			try {
				// Fill the userToUpdate object
				if (datoToUpdate.getIdUser() == null || datoToUpdate.getIdUser().equals("")) {
					datoToUpdate.setIdUser(datoRead.getIdUser());
				}

				if (datoToUpdate.getIdCategoria() == null || datoToUpdate.getIdCategoria().equals("")) {
					datoToUpdate.setIdCategoria(datoRead.getIdCategoria());
				}

				if (datoToUpdate.getIdUnitaMisura() == null || datoToUpdate.getIdUnitaMisura().equals("")) {
					datoToUpdate.setIdUnitaMisura(datoRead.getIdUnitaMisura());
				}
				
				if (datoToUpdate.getValore() == null || datoToUpdate.getValore().equals("")) {
					datoToUpdate.setValore(datoRead.getValore());
				}

				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setInt(1, datoToUpdate.getIdUser());
				preparedStatement.setInt(2, datoToUpdate.getIdCategoria());
				preparedStatement.setInt(3, datoToUpdate.getIdUnitaMisura());
				preparedStatement.setString(4, datoToUpdate.getValore());
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
