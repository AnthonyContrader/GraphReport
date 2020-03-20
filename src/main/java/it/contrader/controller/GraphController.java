package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.DataSetDTO;
import it.contrader.service.DataSetService;
import it.contrader.service.UserService;
import it.contrader.service.UnitaMisuraService;
import it.contrader.service.CategoriaService;

@Controller
@RequestMapping("/graph")
public class GraphController {

	@Autowired
	private DataSetService service;
	
	@Autowired
	private CategoriaService catService;
	
	@Autowired
	private UnitaMisuraService unitaService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/home")
	public String home(HttpServletRequest request) {
		request.setAttribute("x", 5);
		request.setAttribute("y", 15);
		request.setAttribute("label", "denti");
		//setViewHome(request);
		return "graph/graph";
	}
	
	@PostMapping("/creategraph")
	public String creategraph(HttpServletRequest request) {
		request.setAttribute("err", 0 ); // IN ATTESA DI SVILUPPO
		return "graph/graph";
	}
	

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "index";
	}

	private void setViewHome(HttpServletRequest request) {
		setAllHome(request,Long.parseLong(request.getSession().getAttribute("userid").toString()));
	}
	
	private void setViewHome(HttpServletRequest request, Long id) {
		setAllHome(request,id);
	}
	
	public void setAllHome(HttpServletRequest request, Long id) {
		if(request.getSession().getAttribute("usertype").toString().equalsIgnoreCase("admin")) {
			request.setAttribute("listUtente", userService.getAll());
		}
						
		request.setAttribute("list", service.findAllByUtente(id));
		request.setAttribute("listCat", catService.getAll());
		request.setAttribute("listUni", unitaService.getAll());
	}
	
	private void setViewUpdate(HttpServletRequest request,Long cat) {
		request.setAttribute("dataset", service.findAllByUtenteAndCategoria(Long.parseLong(request.getSession().getAttribute("userid").toString()),cat));
		request.setAttribute("listUni", unitaService.getAll());
	}
}
