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
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("usertype") Usertype usertype) {

		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setUsertype(usertype);
		service.update(dto);
		setAll(request);
		return "users";

	}

	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("usertype") Usertype usertype) {
		UserDTO dto = new UserDTO();
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setUsertype(usertype);
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
