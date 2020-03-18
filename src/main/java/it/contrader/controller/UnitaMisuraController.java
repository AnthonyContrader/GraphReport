package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.UnitaMisuraDTO;
import it.contrader.service.UnitaMisuraService;


@Controller
@RequestMapping("/unitamisura")
public class UnitaMisuraController {
	
	@Autowired
	private UnitaMisuraService service;
	
	
	@GetMapping("/welcome")
	public String welcome(HttpServletRequest request) {
		return "welcome";
	}
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "unitamisura/unitamisura";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
		setAll(request);
		return "unitamisura/unitamisura";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.setAttribute("dto", service.read(id));
		return "unitamisura/updateunitamisura";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("nome") String nome) {
		
		UnitaMisuraDTO dto = new UnitaMisuraDTO();
		dto.setId(id);
		dto.setNome(nome);
		service.update(dto);
		setAll(request);
		return "unitamisura/unitamisura";
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("nome") String nome) {
		
		UnitaMisuraDTO dto = new UnitaMisuraDTO();
		dto.setNome(nome);
		service.insert(dto);
		setAll(request);
		return "unitamisura/unitamisura";
	}
	
	
	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		request.setAttribute("dto", service.read(id));
		return "unitamisura/readunitamisura";
	}
	
	private void setAll(HttpServletRequest request) {
		request.setAttribute("list", service.getAll());
	}
	
	
}
