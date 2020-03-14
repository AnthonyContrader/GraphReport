package it.contrader.service;

import java.util.ArrayList;
import java.util.List;

import it.contrader.converter.DataSetConverter;
import it.contrader.dao.DataSetDAO;
import it.contrader.dto.CategoriaDTO;
import it.contrader.dto.DataSetDTO;
import it.contrader.dto.UnitaMisuraDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.DataSet;

/**
 *Grazie all'ereditarietà mi basta specificare i tipi di questa classe per
 *ereditare i metodi della clase AbstractService. Pertanto la classe risulta meno complicata
 *da scrivere, facendoci risparmiare tempo e fatica!
 */
public class DataSetService extends AbstractService<DataSet,DataSetDTO>{
	
	private DataSetDAO datoDAO;
	private DataSetConverter datoConverter;
	
	//Istanzio DAO  e Converter specifici.
	public DataSetService(){
		this.datoDAO = new DataSetDAO();
		this.datoConverter = new DataSetConverter();
	}
	

	public List<DataSetDTO> getAllByUtente(int idUtente) {
		// Ottiene una lista di entità e le restituisce convertendole in DTO
		CategoriaService catService = new CategoriaService();
		UnitaMisuraService unitService = new UnitaMisuraService();
		
		List<DataSet> listaDato = datoDAO.getAllByUtente(idUtente);
		
		List<String> catList = new ArrayList<>(), unitList = new ArrayList<>();
		
		for(DataSet dato : listaDato) {
			catList.add(catService.read(dato.getIdCategoria()).getNome());
			unitList.add(unitService.read(dato.getIdUnitaMisura()).getNome());
		}

		return datoConverter.toDTOList(listaDato,catList,unitList);
	}

	public List<DataSetDTO> getAll() {
		// Ottiene una lista di entità e le restituisce convertendole in DTO
		CategoriaService catService = new CategoriaService();
		UnitaMisuraService unitService = new UnitaMisuraService();
		
		List<DataSet> listaDato = datoDAO.getAll();
		
		List<String> catList = new ArrayList<>(), unitList = new ArrayList<>();
		
		for(DataSet dato : listaDato) {
			catList.add(catService.read(dato.getIdCategoria()).getNome());
			unitList.add(unitService.read(dato.getIdUnitaMisura()).getNome());
		}

		return datoConverter.toDTOList(listaDato,catList,unitList);
	}


	public DataSetDTO read(int id) {
		// Ottiene un'entità e la restituisce convertendola in DTO
		CategoriaService catService = new CategoriaService();
		UnitaMisuraService unitService = new UnitaMisuraService();
		DataSet dato = datoDAO.read(id);
		return datoConverter.toDTO(dato,catService.read(dato.getIdCategoria()).getNome(),unitService.read(dato.getIdUnitaMisura()).getNome());
	}


	public boolean insert(DataSetDTO dto) {
		// Converte un DTO in entità e lo passa al DAO per l'inserimento
		CategoriaService catService = new CategoriaService();
		UnitaMisuraService unitService = new UnitaMisuraService();
		return datoDAO.insert(datoConverter.toEntity(dto,catService.getId(dto.getCategoria()),unitService.getId(dto.getUnitaMisura())));
	}


	public boolean update(DataSetDTO dto) {
		// Converte un DTO in entità e lo passa allo DAO per la modifica
		CategoriaService catService = new CategoriaService();
		UnitaMisuraService unitService = new UnitaMisuraService();
		return datoDAO.update(datoConverter.toEntity(dto,catService.getId(dto.getCategoria()),unitService.getId(dto.getUnitaMisura())));
	}


	public boolean delete(int id,String cat) {	
		CategoriaService catService = new CategoriaService();
		return datoDAO.delete(id,catService.getId(cat));
	}
	
	public static String getUsertype(int id) {
		return UserService.getUsertype(id);
	}
	
	public static List<CategoriaDTO> getAllCategoria(){
		CategoriaService catS = new CategoriaService();
		return catS.getAll();
	}
	
	public static List<UnitaMisuraDTO> getAllUnitaMisura(){
		UnitaMisuraService unitS = new UnitaMisuraService();
		return unitS.getAll();
	}
	
	public static List<UserDTO> getAllUser(){
		UserService userS = new UserService();
		return userS.getAll();
	}


	public boolean existDataSet(int id, String categoria) {
		CategoriaService catService = new CategoriaService();
		return datoDAO.exist(id,catService.getId(categoria));
	}
	
	public boolean existDataSet(int id, String categoria, String unitamisura) {
		CategoriaService catService = new CategoriaService();
		UnitaMisuraService unitService = new UnitaMisuraService();
		return datoDAO.exist(id,catService.getId(categoria),unitService.getId(unitamisura));
	}


	public List<DataSetDTO> readDataSet(int id, String cat) {
		CategoriaService catService = new CategoriaService();
		UnitaMisuraService unitService = new UnitaMisuraService();
		
		List<DataSet> listaDato = datoDAO.getDataSet(id,catService.getId(cat));
		
		List<String> unitList = new ArrayList<>();
		
		for(DataSet dato : listaDato) {
			unitList.add(unitService.read(dato.getIdUnitaMisura()).getNome());
		}

		return datoConverter.toDTOList(listaDato,cat,unitList);
	}


	public boolean deleterow(int id) {
		return datoDAO.deleterow(id);
	}

}
