package it.contrader.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.DataSetDTO;
import it.contrader.service.DataSetService;

@RestController
@RequestMapping("/dataset")
@CrossOrigin(origins = "http://localhost:4200")
public class DataSetController extends AbstractController<DataSetDTO>{

	@Autowired
	private DataSetService service;

	@GetMapping("/deletedataset")
	public boolean deletedataset(@RequestParam("ut") Long idUt,@RequestParam("cat") Long idCat) {
		try {
			service.deleteDataSet(idUt, idCat);
		}catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	@PostMapping("/createdataset")
	public int createdataset(@RequestBody DataSetDTO dto) {
		if(service.insert(dto)!=null)
			return 0;
		
		return 1;
	}
	
	@PostMapping("/delete")
	public boolean delete(@RequestBody Long id) {
		try {
			service.delete(id);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
		
	@PostMapping("/updateds")
	public boolean updateds(@RequestBody DataSetDTO dto) {
	
		if(service.updateDS(dto.getValore(),dto.getCommento(),dto.getId())!=1) 
			return false;
		return true;
	}
	
	@GetMapping("/getAllByUser")
	public List<DataSetDTO> getAllByUser(@RequestParam("id") Long id) {
		return service.findAllByUtente(id);
	}
	
	@GetMapping("/getDataSet")
	public List<DataSetDTO> getDataSet(@RequestParam("id") Long id,@RequestParam("cat") Long cat ) {
		return service.findAllByUtenteAndCategoria(id,cat);
	}
	
	@GetMapping("/countDS")
	public List<DataSetDTO> countDS(@RequestParam("id") Long id) {
		return service.countDS(id);
	}
	
	@PostMapping("/updateDS")
	public boolean updateDS(@RequestBody List<DataSetDTO> dtoList) {
		for(DataSetDTO dto : dtoList) {
			service.update(dto);
		}
		return true;
	}
	
	@GetMapping("/getUMList")
	public List<DataSetDTO> getUMList(@RequestParam("id") Long id,@RequestParam("cat") Long cat) {
		return service.getUMList(id,cat);
	}

	@GetMapping("/findAll")
	public List<DataSetDTO> findAll(@RequestParam("cerca") String daCercare){
		return service.findAll(daCercare);
	}

}
