package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Area;

/**
 * 
 *
 *Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class AreaDAO {

	private final String QUERY_ALL = "SELECT * FROM area";
	private final String QUERY_CREATE = "INSERT INTO area (nome) VALUES (?)";
	private final String QUERY_READ = "SELECT * FROM area WHERE id=?";
	private final String QUERY_ID = "SELECT id FROM area WHERE nome=?";
	private final String QUERY_UPDATE = "UPDATE area SET nome=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM area WHERE id=?";

	public AreaDAO() {

	}

	public List<Area> getAll() {
		List<Area> areaList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Area area;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				area = new Area(nome);
				area.setId(id);
				areaList.add(area);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return areaList;
	}

	public boolean insert(Area areaToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, areaToInsert.getNome());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public Area read(int areaId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, areaId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String nome;

			nome = resultSet.getString("nome");
			Area area = new Area(nome);
			area.setId(resultSet.getInt("id"));

			return area;
		} catch (SQLException e) {
			return null;
		}

	}
	
	public Integer getId(String nome) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ID);
			preparedStatement.setString(1, nome);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt("id");

		} catch (SQLException e) {
			return null;
		}

	}

	public boolean update(Area areaToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (areaToUpdate.getId() == 0)
			return false;

		Area areaRead = read(areaToUpdate.getId());
		if (!areaRead.equals(areaToUpdate)) {
			try {
				// Fill the areaToUpdate object
				if (areaToUpdate.getNome() == null || areaToUpdate.getNome().equals("")) {
					areaToUpdate.setNome(areaRead.getNome());
				}


				// Update the area
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, areaToUpdate.getNome());
				preparedStatement.setInt(2, areaToUpdate.getId());
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
