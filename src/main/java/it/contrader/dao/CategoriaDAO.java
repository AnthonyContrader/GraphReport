package it.contrader.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.Categoria;

/**
 * 
 *
 *Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class CategoriaDAO {

	private final String QUERY_ALL = "SELECT * FROM categoria";
	private final String QUERY_CREATE = "INSERT INTO categoria (nome) VALUES (?)";
	private final String QUERY_READ = "SELECT * FROM categoria WHERE id=?";
	private final String QUERY_ID = "SELECT id FROM categoria WHERE nome=?";
	private final String QUERY_UPDATE = "UPDATE categoria SET nome=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM categoria WHERE id=?";

	public CategoriaDAO() {

	}

	public List<Categoria> getAll() {
		List<Categoria> categoriaList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Categoria categoria;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				categoria = new Categoria(nome);
				categoria.setId(id);
				categoriaList.add(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoriaList;
	}

	public boolean insert(Categoria categoriaToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, categoriaToInsert.getNome());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public Categoria read(int categoriaId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, categoriaId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String nome;

			nome = resultSet.getString("nome");
			Categoria categoria = new Categoria(nome);
			categoria.setId(resultSet.getInt("id"));

			return categoria;
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

	public boolean update(Categoria categoriaToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (categoriaToUpdate.getId() == 0)
			return false;

		Categoria categoriaRead = read(categoriaToUpdate.getId());
		if (!categoriaRead.equals(categoriaToUpdate)) {
			try {
				// Fill the areaToUpdate object
				if (categoriaToUpdate.getNome() == null || categoriaToUpdate.getNome().equals("")) {
					categoriaToUpdate.setNome(categoriaRead.getNome());
				}


				// Update the categoria
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, categoriaToUpdate.getNome());
				preparedStatement.setInt(2, categoriaToUpdate.getId());
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
