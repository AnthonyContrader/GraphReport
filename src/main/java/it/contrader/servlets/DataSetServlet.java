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
		int visUt;
		if(request.getParameter("idUtVis")!=null)
			visUt=Integer.parseInt(request.getParameter("idUtVis"));
		else
			visUt=Integer.parseInt(session.getAttribute("userId").toString());
		List<DataSetDTO> listDTO = service.getAllByUtente(visUt);
		request.setAttribute("list", listDTO);
		request.setAttribute("listCat", DataSetService.getAllCategoria());
		request.setAttribute("listUnit", DataSetService.getAllUnitaMisura());
		request.setAttribute("usertype",DataSetService.getUsertype(Integer.parseInt(request.getSession().getAttribute("userId").toString())));
		if(request.getAttribute("usertype").toString().equalsIgnoreCase("admin"))
			request.setAttribute("listUser", DataSetService.getAllUser());
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSetService service = new DataSetService();
		String mode = request.getParameter("mode");
		int id=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		
		switch (mode.toUpperCase()) {

		case "LIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/dataset/dataset.jsp").forward(request, response);
			break;

		case "READ":
			List<DataSetDTO> dataSet = service.readDataSet(id,request.getParameter("cat"));
			request.setAttribute("dataset", dataSet);
			getServletContext().getRequestDispatcher("/dataset/dsupdate.jsp").forward(request, response);
			break;

		case "INSERT":
			DataSetDTO dto;
			String categoria = request.getParameter("cc");
			String unitUno = request.getParameter("cump");
			String unitDue = request.getParameter("cums");
			if(!service.existDataSet(id,categoria)) {
				dto = new DataSetDTO (id,categoria,unitUno,""); 
				service.insert(dto);
				dto = new DataSetDTO (id,categoria,unitDue,""); 
				service.insert(dto);
			}else { request.setAttribute("err", "1"); }
			updateList(request);
			getServletContext().getRequestDispatcher("/dataset/dataset.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			//username = request.getParameter("username");
			//password = request.getParameter("password");
			//usertype = request.getParameter("usertype");
			//id = Integer.parseInt(request.getParameter("id"));
			//dto = new DataSetDTO (id,username, password, usertype);
			//ans = service.update(dto);
			//updateList(request);
			getServletContext().getRequestDispatcher("/dataset/dataset.jsp").forward(request, response);
			break;

		case "DELETE":
			service.delete(id,request.getParameter("cat"));
			updateList(request);
			getServletContext().getRequestDispatcher("/dataset/dataset.jsp").forward(request, response);
			break;
		}
	}
}