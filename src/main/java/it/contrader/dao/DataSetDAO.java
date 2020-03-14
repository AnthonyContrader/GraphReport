package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.DataSet;

public class DataSetDAO {

	private final String QUERY_ALL = "SELECT * FROM dataset ORDER BY id_user";
	private final String QUERY_ALL_BY_USER = "SELECT * FROM dataset WHERE id_user=? ORDER BY id_categoria";
	private final String QUERY_DATA_SET = "SELECT * FROM dataset WHERE id_user=? AND id_categoria=?";
	private final String QUERY_CREATE = "INSERT INTO dataset (id_user, id_categoria, id_unitamisura, valore) VALUES (?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM dataset WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE dataset SET valore=? WHERE id_user=? AND id_categoria=? AND id_unitamisura=?";
	private final String QUERY_DELETE = "DELETE FROM dataset WHERE id_user=? AND id_categoria=?";
	private final String QUERY_DELETE_ROW = "DELETE FROM dataset WHERE id=?";
	private final String QUERY_EXIST1 = "SELECT COUNT(*) AS conto FROM dataset WHERE id_user=? AND id_categoria=?";
	private final String QUERY_EXIST2 = "SELECT COUNT(*) AS conto FROM dataset WHERE id_user=? AND id_categoria=? AND id_unitamisura=?";
	
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

		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
			preparedStatement.setString(1, datoToUpdate.getValore());
			preparedStatement.setInt(2, datoToUpdate.getIdUser());
			preparedStatement.setInt(3, datoToUpdate.getIdCategoria());
			preparedStatement.setInt(4, datoToUpdate.getIdUnitaMisura());
			int a = preparedStatement.executeUpdate();
			if (a > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			return false;
		}
		
	}

	public boolean delete(int idu,int cat) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, idu);
			preparedStatement.setInt(2, cat);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
		}
		return false;
	}

	public boolean exist(int id, Integer cat) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_EXIST1);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, cat);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.last();
			if (resultSet.getInt("conto") > 0)
					return true;
			
			} catch (SQLException e) {
			}
			return false;
	}
	
	public boolean exist(int id, Integer cat, Integer unita) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_EXIST2);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, cat);
			preparedStatement.setInt(3, unita);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.last();
			if (resultSet.getInt("conto") > 0)
					return true;
			
			} catch (SQLException e) {
			}
			return false;
	}

	public List<DataSet> getDataSet(int id, int cat) {
		List<DataSet> datiList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement statement = (PreparedStatement)connection.prepareStatement(QUERY_DATA_SET);
			statement.setInt(1,id);
			statement.setInt(2,cat);
			ResultSet resultSet = statement.executeQuery();
			DataSet dato;
			while (resultSet.next()) {
				dato = new DataSet(resultSet.getInt("id"), resultSet.getInt("id_user"), resultSet.getInt("id_categoria"), resultSet.getInt("id_unitamisura"), resultSet.getString("valore"));
				
				datiList.add(dato);
			}
		} catch (SQLException e) {
			
		}
		return datiList;
	}

	public boolean deleterow(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE_ROW);
			preparedStatement.setInt(1, id);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
		}
		return false;
	}


}
