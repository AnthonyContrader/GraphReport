package it.contrader.servlets;

import java.util.List;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.contrader.dto.DataSetDTO;
import it.contrader.service.Service;
import it.contrader.service.DataSetService;

/*
 * Per dettagli vedi Guida sez Servlet
 */
public class DataSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DataSetServlet() {
	}
	
	public void updateList(HttpServletRequest request) {
		DataSetService service = new DataSetService();
		final HttpSession session = request.getSession();
		//if()
		List<DataSetDTO>listDTO = service.getAllByUtente(Integer.parseInt(session.getAttribute("userId").toString()));
		request.setAttribute("list", listDTO);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<DataSetDTO> service = new DataSetService();
		String mode = request.getParameter("mode");
		DataSetDTO dto;
		int id;
		boolean ans;

		switch (mode.toUpperCase()) {

		case "LIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/dataset/dataset.jsp").forward(request, response);
			break;

		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null) {
				 getServletContext().getRequestDispatcher("/dataset/readuser.jsp").forward(request, response);
				
			}
			
			else getServletContext().getRequestDispatcher("/dataset/updateuser.jsp").forward(request, response);
			
			break;

		case "INSERT":
			String username = request.getParameter("username").toString();
			String password = request.getParameter("password").toString();
			String usertype = request.getParameter("usertype").toString();
			dto = new DataSetDTO (); //passare parametri
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			username = request.getParameter("username");
			password = request.getParameter("password");
			usertype = request.getParameter("usertype");
			id = Integer.parseInt(request.getParameter("id"));
			dto = new DataSetDTO (id,username, password, usertype);
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