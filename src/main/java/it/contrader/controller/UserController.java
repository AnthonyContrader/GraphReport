package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.UserDTO;
import it.contrader.model.User.Usertype;
import it.contrader.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/login")
	public String login(HttpServletRequest request, @RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {

		UserDTO userDTO = service.findByUsernameAndPassword(username, password);
		
		if(userDTO!=null) {
			request.getSession().setAttribute("user", userDTO);
			request.getSession().setAttribute("userid", userDTO.getId());
			request.getSession().setAttribute("usertype", userDTO.getUsertype().toString());
			return "homeStructure";
		}else return "index";
	}
	
	@GetMapping("/welcome")
	public String welcome(HttpServletRequest request) {
		return "welcome";
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
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("usertype") Usertype usertype,
			@RequestParam("nome") String nome, @RequestParam("cognome") String cognome,
			@RequestParam("email") String email, @RequestParam("citta") String citta, 
			@RequestParam("nazione") String nazione) {

		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setUsertype(usertype);
		dto.setNome(nome);
		dto.setCognome(cognome);
		dto.setEmail(email);
		dto.setCitta(citta);
		dto.setNazione(nazione);
		service.update(dto);
		setAll(request);
		return "users";

	}

	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("usertype") Usertype usertype,
			@RequestParam("nome") String nome, @RequestParam("cognome") String cognome,
			@RequestParam("email") String email, @RequestParam("citta") String citta, 
			@RequestParam("nazione") String nazione) {
		UserDTO dto = new UserDTO();
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setUsertype(usertype);
		dto.setNome(nome);
		dto.setCognome(cognome);
		dto.setEmail(email);
		dto.setCitta(citta);
		dto.setNazione(nazione);
		service.insert(dto);
		setAll(request);
		return "users";
	}
	
	@PostMapping("/register")
	public String register(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("usertype") Usertype usertype,
			@RequestParam("nome") String nome, @RequestParam("cognome") String cognome,
			@RequestParam("email") String email, @RequestParam("citta") String citta, 
			@RequestParam("nazione") String nazione) {
		UserDTO dto = new UserDTO();
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setUsertype(usertype);
		dto.setNome(nome);
		dto.setCognome(cognome);
		dto.setEmail(email);
		dto.setCitta(citta);
		dto.setNazione(nazione);
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
