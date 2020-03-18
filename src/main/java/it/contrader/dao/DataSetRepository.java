package it.contrader.dao;

import javax.transaction.Transactional;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.DataSet;

@Repository
@Transactional
public interface DataSetRepository extends CrudRepository<DataSet,Long>{

	//QUERY_ALL = "SELECT * FROM dataset ORDER BY id_user";
	//QUERY_ALL_BY_USER = "SELECT * FROM dataset WHERE id_user=? ORDER BY id_categoria";
	//QUERY_DATA_SET = "SELECT * FROM dataset WHERE id_user=? AND id_categoria=?";
	//QUERY_CREATE = "INSERT INTO dataset (id_user, id_categoria, id_unitamisura, valore) VALUES (?,?,?,?)";
	//QUERY_READ = "SELECT * FROM dataset WHERE id=?";
	//QUERY_UPDATE = "UPDATE dataset SET valore=? WHERE id_user=? AND id_categoria=? AND id_unitamisura=?";
	//QUERY_DELETE = "DELETE FROM dataset WHERE id_user=? AND id_categoria=?";
	//QUERY_DELETE_ROW = "DELETE FROM dataset WHERE id=?";
	//QUERY_EXIST1 = "SELECT COUNT(*) AS conto FROM dataset WHERE id_user=? AND id_categoria=?";
	//QUERY_EXIST2 = "SELECT COUNT(*) AS conto FROM dataset WHERE id_user=? AND id_categoria=? AND id_unitamisura=?";
	
	List<DataSet> findAllByUtente(Long utente);
	
	List<DataSet> findAllByUtenteAndCategoria(Long utente, Long categoria);

}
