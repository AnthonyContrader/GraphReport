package it.contrader.servlets;

import java.util.List;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.UnitaMisuraDTO;
import it.contrader.service.Service;
import it.contrader.service.UnitaMisuraService;

/*
 * Per dettagli vedi Guida sez Servlet
 */
public class UnitaMisuraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UnitaMisuraServlet() {
	}
	
	public void updateList(HttpServletRequest request) {
		Service<UnitaMisuraDTO> service = new UnitaMisuraService();
		List<UnitaMisuraDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<UnitaMisuraDTO> service = new UnitaMisuraService();
		String mode = request.getParameter("mode");
		UnitaMisuraDTO dto;
		int id;
		boolean ans;

		switch (mode.toUpperCase()) {

		case "LIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/unitamisura/unitamisura.jsp").forward(request, response);
			break;

		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null) {
				 getServletContext().getRequestDispatcher("/unitamisura/readunitamisura.jsp").forward(request, response);
				
			}
			
			else getServletContext().getRequestDispatcher("/unitamisura/updateunitamisura.jsp").forward(request, response);
			
			break;

		case "INSERT":
			String nome = request.getParameter("nome").toString();
			dto = new UnitaMisuraDTO (nome);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/unitamisura/unitamisura.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			nome = request.getParameter("nome");
			id = Integer.parseInt(request.getParameter("id"));
			dto = new UnitaMisuraDTO (id,nome);
			ans = service.update(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/unitamisura/unitamisura.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/unitamisura/unitamisura.jsp").forward(request, response);
			break;
		}
	}
}