<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.DataSetDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DataSet</title>
<link href="css/toniostyle.css" rel="stylesheet">
</head>
<body>
	<div class="cols half">
<%
		List<DataSetDTO> list = (List <DataSetDTO>) request.getAttribute("list");
		String cat = "";
		boolean first=true;
		for (DataSetDTO ds : list){
			if(!cat.equalsIgnoreCase(ds.getCategoria())){
				if(!first){
					%>
					</div></div>
					<%
				}else first=false;
				cat=ds.getCategoria();
			%>
			<div class="newCat">
			<a href="" class="linkCRUD">Elimina</a>
			<a href="" class="linkCRUD">Modifica</a>
			<h1 class="posCat"><%= cat %></h1>
			<div class="center">
			<% }
			String[] valori = ds.getValore().split("_");
			%>
				<div class="cols marginBot">
					<div class="th">
						<%= ds.getUnitaMisura() %>
					</div>
					<%
					for(int i=0;i<valori.length;i++){
					%>
						<div class="tr <% if(i%2==0){ %>grey<% } %>">
							<%= valori[i] %>
						</div>
					<%
					} 
					%>
				</div>
			
			

		
		<%
			}
		%>
		</div></div>
	</div>	
	<div class="cols half">
	<h1>qui qualcos'altro</h1>
	</div>
</body>
</html>