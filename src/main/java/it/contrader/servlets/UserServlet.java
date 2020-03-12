package it.contrader.servlets;

import java.util.List;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.contrader.dto.UserDTO;
import it.contrader.service.Service;
import it.contrader.service.UserService;

/*
 * Per dettagli vedi Guida sez Servlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
	}
	
	public void updateList(HttpServletRequest request) {
		Service<UserDTO> service = new UserService();
		List<UserDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<UserDTO> service = new UserService();
		String mode = request.getParameter("mode");
		UserDTO dto;
		int id;
		boolean ans;

		switch (mode.toUpperCase()) {

		case "USERLIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
			break;

		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null) {
				 getServletContext().getRequestDispatcher("/user/readuser.jsp").forward(request, response);
				
			}
			
			else getServletContext().getRequestDispatcher("/user/updateuser.jsp").forward(request, response);
			
			break;

		case "INSERT":
			String username = request.getParameter("username").toString();
			String password = request.getParameter("password").toString();
			String usertype = request.getParameter("usertype").toString();
			String nome = request.getParameter("nome").toString();
			String cognome = request.getParameter("cognome").toString();
			String email = request.getParameter("email").toString();
			String citta = request.getParameter("citta").toString();
			String nazione = request.getParameter("nazione").toString();
			dto = new UserDTO (username,password,usertype,nome,cognome,email,citta,nazione);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			username = request.getParameter("username");
			password = request.getParameter("password");
			usertype = request.getParameter("usertype");
			nome = request.getParameter("nome").toString();
			cognome = request.getParameter("cognome").toString();
			email = request.getParameter("email").toString();
			citta = request.getParameter("citta").toString();
			nazione = request.getParameter("nazione").toString();
			id = Integer.parseInt(request.getParameter("id"));
			dto = new UserDTO (id,username, password, usertype,nome,cognome,email,citta,nazione);
			ans = service.update(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
			break;
		}
	}
}