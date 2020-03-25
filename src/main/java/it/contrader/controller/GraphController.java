package it.contrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public boolean addset(@RequestBody DataGraphDTO dtoDGx,@RequestBody DataGraphDTO dtoDGy) {
		
		service.insertMtM(dtoDGx);
		service.insertMtM(dtoDGy);
		
		return true;
	}
				
	@GetMapping("/delete")
	public boolean delete(@RequestBody Long id) {
		serviceMtM.deleteByGraph(id);
		service.delete(id);
		
		return true;
	}
	
}
