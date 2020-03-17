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

@Controller
@RequestMapping("/dataset")
public class DataSetController {

	@Autowired
	private DataSetService service;

	@GetMapping("/home")
	public String home(HttpServletRequest request) {
		request.setAttribute("list", service.findAllByUtente(Long.parseLong(request.getSession().getAttribute("user").toString())));
		return "dataset/dataset";
	}
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "users";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
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

	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("utente") Long utente,
			@RequestParam("categoria") Long categoria, @RequestParam("unitaMisura") Long unitaMisura, @RequestParam("valore") String valore) {
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

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "readuser";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "index";
	}

	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
}
