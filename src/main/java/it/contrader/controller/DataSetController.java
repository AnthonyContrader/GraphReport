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
	public String createdataset(HttpServletRequest request, @RequestParam("cat") Long cat, @RequestParam("ump") Long ump, @RequestParam("ums") Long ums) {
		DataSetDTO ds = new DataSetDTO();
		ds.setUtente(Long.parseLong(request.getSession().getAttribute("userid").toString()));
		ds.setCategoria(cat);
		ds.setUnitaMisura(ump);
		ds.setValore(" _");
		service.insert(ds);
		ds.setUnitaMisura(ums);
		service.insert(ds);
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
	public String addum(HttpServletRequest request, @RequestParam("cat") Long cat, @RequestParam("n") int n, @RequestParam("unit") Long um) {
		DataSetDTO dto = new DataSetDTO();
		dto.setId(id);
		dto.setUtente(utente);
		dto.setCategoria(categoria);
		dto.setUnitaMisura(unitaMisura);;
		dto.setValore(valore);;
		service.insert(dto);
		setAll(request);
		return "users";
	}
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "users";
	}

	
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "updateuser";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("utente") Long utente,
			@RequestParam("categoria") Long categoria, @RequestParam("unitaMisura") Long unitaMisura, @RequestParam("valore") String valore) {

		DataSetDTO dto = new DataSetDTO();
		dto.setId(id);
		dto.setUtente(utente);
		dto.setCategoria(categoria);
		dto.setUnitaMisura(unitaMisura);;
		dto.setValore(valore);;
		service.update(dto);
		setAll(request);
		return "users";

	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "index";
	}

	private void setViewHome(HttpServletRequest request) {
		if(request.getSession().getAttribute("usertype").toString().equalsIgnoreCase("admin")) {
			request.setAttribute("listUtente", userService.getAll());
		}
						
		request.setAttribute("list", service.findAllByUtente(Long.parseLong(request.getSession().getAttribute("userid").toString())));
		request.setAttribute("listCat", catService.getAll());
		request.getSession().setAttribute("listUni", unitaService.getAll());
			
	}
	
	private void setViewUpdate(HttpServletRequest request,Long cat) {
		request.setAttribute("dataset", service.findAllByUtenteAndCategoria(Long.parseLong(request.getSession().getAttribute("userid").toString()),cat));
	}
}
