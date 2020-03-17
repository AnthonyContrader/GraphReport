package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.CategoriaDTO;
import it.contrader.service.CategoriaService;


@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping("/welcome")
	public String welcome(HttpServletRequest request) {
		return "welcome";
	}
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request){
		setAll(request);
		return "categoria";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
		setAll(request);
		return "categoria";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "updatecategoria";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("nome") String nome) {
		
		CategoriaDTO dto = new CategoriaDTO();
		dto.setId(id);
		dto.setNome(nome);
		service.update(dto);
		setAll(request);
		return "categoria";
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("nome") String nome) {
		
		CategoriaDTO dto = new CategoriaDTO();
		dto.setNome(nome);
		service.insert(dto);
		setAll(request);
		return "categoria";
	}
	
	
	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "readcategoria";
	}
	
	
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
	

}
