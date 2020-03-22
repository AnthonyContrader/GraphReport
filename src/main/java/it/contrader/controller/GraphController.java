package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.GraphDTO;
import it.contrader.model.Graph.TipoGrafico;
import it.contrader.model.Graph.Tema;
import it.contrader.service.DataGraphService;
import it.contrader.service.DataSetService;
import it.contrader.service.GraphService;
import it.contrader.service.UserService;

@Controller
@RequestMapping("/graph")
public class GraphController {

	@Autowired
	private GraphService service;
	
	@Autowired
	private DataSetService serviceDS;
	
	@Autowired
	private DataGraphService serviceMtM;
	
	@Autowired
	private UserService serviceU;

	@GetMapping("/home")
	public String home(HttpServletRequest request) {
		setViewHome(request);
		return "graph/graph";
	}
	
	@PostMapping("/creategraph")
	public String creategraph(HttpServletRequest request, @RequestParam("tit") String titolo, @RequestParam("idassex") Long ax, @RequestParam("idassey") Long ay ) {
		
		GraphDTO dto = new GraphDTO();
		dto.setTitolo(titolo);
		dto.setPareto(false);
		dto.setLegenda(false);
		dto.setPosTitolo("top_center");
		dto.setTema(Tema.ligth1);
		dto.setTipografico(TipoGrafico.column);
		dto.setZoom(false);
		dto=service.insert(dto);
		
		service.insertMtM(ax,dto.getId());
		service.insertMtM(ay,dto.getId());
		
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
			request.setAttribute("listUtente", serviceU.getAll());
		}
		request.setAttribute("listGraph", service.getAllByUser(id));
		request.setAttribute("listC", serviceDS.findAllByUtente(id));
						
		
	}
	
	private void setViewUpdate(HttpServletRequest request,Long cat) {
		
	}
}
