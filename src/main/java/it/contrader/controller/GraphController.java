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

import it.contrader.dto.DataGraphDTO;
import it.contrader.dto.GraphDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.DataGraphService;
import it.contrader.service.GraphService;

@RestController
@RequestMapping("/graph")
@CrossOrigin(origins = "http://localhost:4200")
public class GraphController extends AbstractController<GraphDTO>{

	@Autowired
	private GraphService service;
	
	@Autowired
	private DataGraphService serviceMtM;

	@PostMapping("/creategraph")
	public boolean creategraph(@RequestBody GraphDTO dto,@RequestBody DataGraphDTO dtoDGx,@RequestBody DataGraphDTO dtoDGy) {
		
		service.insert(dto);
		
		service.insertMtM(dtoDGx);
		service.insertMtM(dtoDGy);
		
		return true;
	}
	
	@PostMapping("/addset")
	public boolean addset(@RequestBody List<DataGraphDTO> dto) {
		for(DataGraphDTO d : dto) 
			service.insertMtM(d); 
		return true;
	}
				
	@GetMapping("/delete")
	public boolean delete(@RequestParam("id") Long id) {
		serviceMtM.deleteByGraph(id);
		service.delete(id);
		
		return true;
	}
	
	@GetMapping("/getAllByUser")
	public List<GraphDTO> getAllByUser(@RequestParam("id") Long id) {
		return service.getAllByUser(id);
	}
	
	@GetMapping("/getAllByGraph")
	public List<DataGraphDTO> getAllByGraph(@RequestParam("id") Long id) {
		return serviceMtM.getListValue(id);
	}
	
	@GetMapping("/findAll")
	public List<GraphDTO> findAll(@RequestParam("cerca") String daCercare){
		return service.findAll(daCercare);
	}
}
