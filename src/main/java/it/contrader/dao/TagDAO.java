package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Tag;

public class TagDAO {
	
	private Connection connection ;
	private final String QUERY_ALL = "SELECT * FROM tag";
	private final String QUERY_CREATE = "INSERT INTO tag (nome) VALUES (?)";
	private final String QUERY_READ = "SELECT * FROM tag WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE tag SET nome=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM tag WHERE id=?";
	private final String QUERY_ID = "SELECT id FROM tag WHERE nome=?";
	
	public TagDAO() {
		connection= ConnectionSingleton.getInstance();
	}

	public List<Tag> getAll() {
		List<Tag> tagList = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Tag tag;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				tag = new Tag(id,nome);
				tagList.add(tag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tagList;
	}

	public boolean insert(Tag tagToInsert) {
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, tagToInsert.getNomeTag());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public Tag read(int tagId) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, tagId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String nome = resultSet.getString("nome");
			Tag tag = new Tag(nome);
			tag.setId(resultSet.getInt("id"));

			return tag;
		} catch (SQLException e) {
			return null;
		}

	}

	public boolean update(Tag tagToUpdate) {
		// Check if id is present
		if (tagToUpdate.getId() == 0)
			return false;
		Tag tagRead = read(tagToUpdate.getId());
		if (!tagRead.equals(tagToUpdate)) {
			try {
				// Fill the userToUpdate object
				if (tagToUpdate.getNomeTag() == null || tagToUpdate.getNomeTag().equals("")) {
					tagToUpdate.setNomeTag(tagRead.getNomeTag());
				}
				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, tagToUpdate.getNomeTag());
				preparedStatement.setInt(2, tagToUpdate.getId());
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
