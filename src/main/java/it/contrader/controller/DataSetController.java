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
@RequestMapping("/dataset")
public class DataSetController {

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
		setViewHome(request);
		return "dataset/dataset";
	}
	
	@PostMapping("/viewUt")
	public String viewUt(HttpServletRequest request, @RequestParam("idUtVis") Long id) {
		request.setAttribute("idUtVis", id);
		setViewHome(request, id);
		return "dataset/dataset";
	}
	
	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long cat) {
		setViewUpdate(request, cat);
		return "dataset/dsupdate";
	}
	
	@GetMapping("/deletedataset")
	public String deletedataset(HttpServletRequest request, @RequestParam("id") Long id) {
		service.deleteDataSet(Long.parseLong(request.getSession().getAttribute("userid").toString()), id);
		setViewHome(request);
		return "dataset/dataset";
	}
	
	@PostMapping("/createdataset")
	public String createdataset(HttpServletRequest request, @RequestParam("cat") String cat, @RequestParam("ump") String ump, @RequestParam("ums") String ums) {
		String[] arrC= cat.split("]");
		Long ut = Long.parseLong(request.getSession().getAttribute("userid").toString());
		if(!service.exist(ut,Long.parseLong(arrC[0]))) {
			DataSetDTO ds = new DataSetDTO();
			ds.setUtente(ut);
			ds.setCategoria(Long.parseLong(arrC[0]));
			ds.setCategoriaN(arrC[1]);
			ds.setUnitaMisura(Long.parseLong(ump.split("]")[0].toString()));
			ds.setUnitaMisuraN(ump.split("]")[1].toString());
			ds.setValore(" _");
			ds.setCommento("");
			service.insert(ds);
			ds.setUnitaMisura(Long.parseLong(ums.split("]")[0].toString()));
			ds.setUnitaMisuraN(ums.split("]")[1].toString());
			service.insert(ds);
		}else{
			request.setAttribute("err", 1);
		}
		setViewHome(request);
		return "dataset/dataset";
	}
	
	@PostMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("cat") Long cat) {
		service.delete(id);
		setViewUpdate(request,cat);
		return "dataset/dsupdate";
	}
	
	@PostMapping("/addum")
	public String addum(HttpServletRequest request, @RequestParam("cat") Long cat, @RequestParam("n") int n, @RequestParam("unit") String um) {
		String[] arrUM= um.split("]");
		Long ut = Long.parseLong(request.getSession().getAttribute("userid").toString());
		if(!service.exist(ut,cat,Long.parseLong(arrUM[0]))) {
			DataSetDTO dto = new DataSetDTO();
			String valore="";
			dto.setUtente(ut);
			dto.setCategoria(cat);
			dto.setUnitaMisura(Long.parseLong(arrUM[0]));
			dto.setUnitaMisuraN(arrUM[1]);
			dto.setCommento("");
			for(int i=0; i<n;i++)
				valore+=" _";
			dto.setValore(valore);
			service.insert(dto);
		}else {
			request.setAttribute("err", 1);
		}
		setViewUpdate(request,cat);
		return "dataset/dsupdate";
	}
			
	@PostMapping("/updateds")
	public String updateds(HttpServletRequest request, @RequestParam("cat") Long cat, @RequestParam("valore") String valore) {
		String[] arrDS = valore.split("}"), ds;
		for(int i=0;i < arrDS.length ;i++) {
			ds = arrDS[i].split("]");
			if(service.updateDS(ds[2].toString(),ds[1].toString(),Long.parseLong(ds[0].toString()))!=1) {
				request.setAttribute("err", 3);
			}
		}
		
		setViewUpdate(request,cat);
		return "dataset/dsupdate";

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
