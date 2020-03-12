package it.contrader.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.UnitaMisura;

public class UnitaMisuraDAO implements DAO<UnitaMisura> {
	
	private Connection connection ;
	private final String QUERY_ALL = "SELECT * FROM unitamisura";
	private final String QUERY_CREATE = "INSERT INTO unitamisura (nome) VALUES (?)";
	private final String QUERY_READ = "SELECT * FROM unitamisura WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE unitamisura SET nome=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM unitamisura WHERE id=?";
	private final String QUERY_ID = "SELECT id FROM unitamisura WHERE nome=?";
	
	public UnitaMisuraDAO() {
		connection= ConnectionSingleton.getInstance();
	}

	public List<UnitaMisura> getAll() {
		List<UnitaMisura> unitamisuraList = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			UnitaMisura unitamisura;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				unitamisura = new UnitaMisura(id,nome);
				unitamisuraList.add(unitamisura);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unitamisuraList;
	}

	public boolean insert(UnitaMisura unitamisuraToInsert) {
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, unitamisuraToInsert.getNome());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public UnitaMisura read(int unitamisuraId) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, unitamisuraId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String nome = resultSet.getString("nome");
			UnitaMisura unitamisura = new UnitaMisura(nome);
			unitamisura.setId(resultSet.getInt("id"));

			return unitamisura;
		} catch (SQLException e) {
			return null;
		}

	}

	public boolean update(UnitaMisura unitamisuraToUpdate) {
		// Check if id is present
		if (unitamisuraToUpdate.getId() == 0)
			return false;
		UnitaMisura unitamisuraRead = read(unitamisuraToUpdate.getId());
		if (!unitamisuraRead.equals(unitamisuraToUpdate)) {
			try {
				// Fill the userToUpdate object
				if (unitamisuraToUpdate.getNome() == null || unitamisuraToUpdate.getNome().equals("")) {
					unitamisuraToUpdate.setNome(unitamisuraRead.getNome());
				}
				// Update the unita di misura
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, unitamisuraToUpdate.getNome());
				preparedStatement.setInt(2, unitamisuraToUpdate.getId());
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
	
	public Integer getId(String nome) {
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

}
