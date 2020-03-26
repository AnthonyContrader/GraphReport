package it.contrader.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.contrader.dto.LoginDTO;
import it.contrader.dto.UserDTO;
//import it.contrader.model.User.Usertype;
import it.contrader.service.UserService;


/**
 * 
 * Questa classe estende AbstractController con tipo UserDTO.
 * In aggiunta ai metodi di CRUD si implementa il metodo di login.
 * 
 * @author Vittorio Valent & Girolamo Murdaca
 * 
 * @param<UserDTO>
 * 
 * @see AbstractController
 *
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController extends AbstractController<UserDTO>{
	
	@Autowired
	private UserService userService;


	//POST Angular a UserDTO
	@PostMapping(value = "/login")
	public UserDTO login(@RequestBody LoginDTO loginDTO ) {
		return userService.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
	}

	@GetMapping("/deleteById")
	public Boolean deleteById(@RequestParam("id") Long id) {
		try {
			userService.delete(id);
		}catch(Exception e) {
			return false;
		}
		return true;
	}


	@PostMapping("/update")
	public UserDTO update(@RequestBody UserDTO dto) {
		return userService.update(dto);
//		if(!request.getSession().getAttribute("usertype").toString().equalsIgnoreCase(usertype.toString()))
//			request.getSession().setAttribute("usertype",usertype.toString());
//		return "users";
	}
	
	@PostMapping("/register")
	public UserDTO register(@RequestBody UserDTO dto) {
		return userService.insert(dto);
	}


//	@GetMapping("/logout")
//	public String logout(HttpServletRequest request) {
//		request.getSession().invalidate();
//		return "index";
//	}

//	private void setAll(HttpServletRequest request) {
//		if(request.getSession().getAttribute("usertype").toString().equalsIgnoreCase("user")){
//			request.setAttribute("user", service.read(Long.parseLong(request.getSession().getAttribute("userid").toString())));
//		}
//		else {
//			request.setAttribute("list", service.getAll());
//		}
//	}
}