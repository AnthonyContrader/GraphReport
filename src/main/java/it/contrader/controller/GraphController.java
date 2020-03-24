package it.contrader.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.DataGraphDTO;
import it.contrader.dto.DataSetDTO;
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
	public String creategraph(HttpServletRequest request, @RequestParam("tit") String titolo, @RequestParam("assex") Long ax, @RequestParam("assey") Long ay ) {
		
		GraphDTO dto = new GraphDTO();
		dto.setTitolo(titolo);
		dto.setPareto(false);
		dto.setLegenda(true);
		dto.setPosTitolo("top_center");
		dto.setTema(Tema.ligth1);
		dto.setTipografico(TipoGrafico.line);
		dto.setZoom(false);
		dto=service.insert(dto);
		
		service.insertMtM(ax,dto.getId(),'x');
		service.insertMtM(ay,dto.getId(),'y');
		
		setViewHome(request);
		return "graph/graph";
	}
	
	@PostMapping("/addset")
	public String addset(HttpServletRequest request, @RequestParam("assex") Long ax, @RequestParam("assey") Long ay, @RequestParam("id") Long id ) {
		
		service.insertMtM(ax,id,'x');
		service.insertMtM(ay,id,'y');
		
		request.setAttribute("mode", "up");
		setViewUpdate(request, id);
		return "graph/showGraph";
	}
	
	@PostMapping("/viewUt")
	public String viewUt(HttpServletRequest request, @RequestParam("idUtVis") Long id) {
		setViewHome(request,id);
		return "graph/graph";
	}
	
	@GetMapping("/showUpdate")
	public String showGraph(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("mode") String mode) {
		request.setAttribute("mode", mode);
		
		setViewUpdate(request, id);
		return "graph/showGraph";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("mode") String mode, @RequestParam("id") Long id, @RequestParam("titolo") String titolo, @RequestParam("altTitolo") String altT, 
			@RequestParam("posTitolo") String posT, @RequestParam("tipo") TipoGrafico tipo, @RequestParam("tema") Tema tema,
			@RequestParam("legenda") boolean legenda, @RequestParam("zoom") boolean zoom, @RequestParam("pareto") boolean pareto) {
		
		GraphDTO dto = new GraphDTO();
		dto.setId(id);
		dto.setTitolo(titolo);
		dto.setPosTitolo(altT+"_"+posT);
		dto.setTipografico(tipo);
		dto.setTema(tema);
		dto.setLegenda(legenda);
		dto.setZoom(zoom);
		dto.setPareto(pareto);
		service.update(dto);
		
		request.setAttribute("mode", mode);
		setViewUpdate(request,id);
		return "graph/showGraph";
	}
			
	@GetMapping("/delete")
	public String delete(HttpServletRequest request,@RequestParam("id") Long id) {
		serviceMtM.deleteByGraph(id);
		service.delete(id);
		setViewHome(request);
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
	
	private void setViewUpdate(HttpServletRequest request,Long id) {
		List<DataGraphDTO> dtolist= serviceMtM.getListValue(id);
		GraphDTO graph = service.read(id);
		String[] asseX = null, asseY = null;
		String labelx="",labely="";
		List<String> listValori = new ArrayList<String>(),
				tagY = new ArrayList<String>();
		
		int i=0;
		for(DataGraphDTO dg : dtolist) {
			i++;
			DataSetDTO ds = serviceDS.read(dg.getDataSetId());
			 if(dg.getAsse()=='x') {
				asseX=ds.getValore().split("_");
			}else {
				asseY=ds.getValore().split("_");
				tagY.add(ds.getCommento());
			}
			 if(i%2==0) {
				 try {
					 Double.parseDouble(asseX[0]);
						labelx="{ x: ";
						labely=", y: ";
					}catch(Exception e) {
						if(graph.getLegenda())
							labelx="{ name: '";
						else
							labelx="{ label: '";
						labely="', y: ";
						}
				 
				 	String arrayValue ="";
					for(int j=0;j<asseX.length;j++) {
						arrayValue+= labelx+ asseX[j] +labely+ asseY[j] +" },";
					}
					listValori.add(arrayValue.substring(0,arrayValue.length()-1));
			 }
		}
		
		
		
		if(request.getAttribute("mode").toString().equalsIgnoreCase("up")) {
			request.setAttribute("listGraph", service.getAllByUser(Long.parseLong(request.getSession().getAttribute("userid").toString())));
			request.setAttribute("listC", serviceDS.findAllByUtente(Long.parseLong(request.getSession().getAttribute("userid").toString())));
		}
		request.setAttribute("tagY", tagY);
		request.setAttribute("id", id);
		request.setAttribute("arrV", listValori);
		request.setAttribute("graph", graph);
	}
}
