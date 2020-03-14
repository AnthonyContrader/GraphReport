package it.contrader.servlets;

import java.util.List;



import java.io.IOException;
import java.sql.SQLException;

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
	
	public void updateView(HttpServletRequest request) {
		DataSetService service = new DataSetService();
		List<DataSetDTO> dataSet = service.readDataSet(Integer.parseInt(request.getSession().getAttribute("userId").toString()),request.getParameter("cat"));
		request.setAttribute("listUnit", DataSetService.getAllUnitaMisura());
		request.setAttribute("dataset", dataSet);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSetService service = new DataSetService();
		String mode = request.getParameter("mode");
		DataSetDTO dto;
		String categoria;
		String unitUno;
		String unitDue;
		int id=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		
		switch (mode.toUpperCase()) {

		case "LIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/dataset/dataset.jsp").forward(request, response);
			break;

		case "READ":
			updateView(request);
			getServletContext().getRequestDispatcher("/dataset/dsupdate.jsp").forward(request, response);
			break;

		case "INSERT":
			categoria = request.getParameter("cc");
			unitUno = request.getParameter("cump");
			unitDue = request.getParameter("cums");
			if(!service.existDataSet(id,categoria)) {
				dto = new DataSetDTO (id,categoria,unitUno," _"); 
				service.insert(dto);
				dto = new DataSetDTO (id,categoria,unitDue," _");
				service.insert(dto);
			}else { request.setAttribute("err", "1"); }
			updateList(request);
			getServletContext().getRequestDispatcher("/dataset/dataset.jsp").forward(request, response);
			break;
			
		case "INSERTROW":
			categoria = request.getParameter("cat");
			unitUno = request.getParameter("unit");
			String value="";
			for(int i=0;i<Integer.parseInt(request.getParameter("n"));i++) {
				value+=" _";
			}
			if(!service.existDataSet(id,categoria,unitUno)) {
				dto = new DataSetDTO (id,categoria,unitUno,value); 
				service.insert(dto);
			}else { request.setAttribute("err", "1"); }
			updateView(request);
			getServletContext().getRequestDispatcher("/dataset/dsupdate.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			categoria = request.getParameter("cat");
			int n = Integer.parseInt(request.getParameter("dstot"));
			String[] split;
			
			for(int i=0;i<n;i++) {
				split=request.getParameter("ds"+i).split("!");
				dto = new DataSetDTO (id,categoria, split[0], split[1]);
				if(!service.update(dto))
					request.setAttribute("err", "3");
				
			}
		
			updateView(request);
			getServletContext().getRequestDispatcher("/dataset/dsupdate.jsp").forward(request, response);
			break;

		case "DELETE":
			service.delete(id,request.getParameter("cat"));
			updateList(request);
			getServletContext().getRequestDispatcher("/dataset/dataset.jsp").forward(request, response);
			break;
			
		case "DELETEROW":
			if(!service.deleterow(Integer.parseInt(request.getParameter("id")))) {
				request.setAttribute("err", "2");
			}
			updateView(request);
			getServletContext().getRequestDispatcher("/dataset/dsupdate.jsp").forward(request, response);
			break;
		}
	}
}