<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.DataSetDTO" import="it.contrader.dto.UserDTO" import="it.contrader.dto.CategoriaDTO" import="it.contrader.dto.UnitaMisuraDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DataSet</title>
<link href="css/toniostyle.css" rel="stylesheet">
<script type="text/javascript" src="js/toniojs.js"></script>
</head>
<body>

	<div class="cols half">
<%
		List<DataSetDTO> list = (List <DataSetDTO>) request.getAttribute("list");
		List<CategoriaDTO> listC = (List <CategoriaDTO>) request.getAttribute("listCat");
		List<UnitaMisuraDTO> listUM = (List <UnitaMisuraDTO>) request.getAttribute("listUnit");
		String cat = "";
		boolean first=true;
		
		if(request.getAttribute("usertype").toString().equalsIgnoreCase("admin")){
			List<UserDTO> listU = (List <UserDTO>) request.getAttribute("listUser");
			%>
			<form class="center" id="visDataSetUt" action="DataSetServlet" style="width:max-content">
			<label>Visualizza DataSet dell'utente</label>
			<input type="text" name="mode" value="list" style="display:none;" />
				<select name="idUtVis" onchange="document.getElementById('visDataSetUt').submit();" style="width:max-content">
					<option value="<%=session.getAttribute("userId")%>">Tuoi Personali</option>
					<%
					for(UserDTO u : listU){
						if(u.getId()!=Integer.parseInt(session.getAttribute("userId").toString())){
						%>
						
						<option value="<%=u.getId()%>" <% if(request.getParameter("idUtVis")!=null){ if(Integer.parseInt(request.getParameter("idUtVis"))==u.getId()){ %>selected<% } } %>><%=u.getUsername()%></option>
						
						<%
						}
					}
					%>
				</select>
			</form>
			<%
		}
		
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
				<% if(ds.getUser()==Integer.parseInt(session.getAttribute("userId").toString())){ %>
				<div class="divCRUD" style="width:20%;">
					<a href="DataSetServlet?mode=read&cat=<%=cat%>" ><div class="linkCRUD">Modifica</div></a>
					<a href="DataSetServlet?mode=delete&cat=<%=cat%>" onclick="return confDelDS()"><div class="linkCRUD">Elimina</div></a>
				</div>
				<% } %>
			<h1><%= cat %></h1>
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
	<h1>Crea nuovo DataSet</h1>
	<p class="center">In seguito alla creazione sarà possibile inserire nuovi valori e nuove tipologie di valori.</p>
	<form class="center" action="DataSetServlet" style="min-width:270px" onsubmit="return verificaCrea()">
		<label><strong>Categoria</strong></label>
		<select id="cc" name="cc">
			<option value="" style="color:grey">Scegli una categoria</option>
			<%
			for(CategoriaDTO c : listC){
				%>
				
				<option value="<%=c.getNome()%>"><%=c.getNome()%></option>
				
				<%
				}
			%>
		</select>
		<label><strong>Unità di misura:</strong></label>
		<br>
		<select id="cump" name="cump">
			<option value="" style="color:grey">Primo set di valori</option>
			<%
			for(UnitaMisuraDTO um : listUM){
				%>
				
				<option value="<%=um.getNome()%>"><%=um.getNome()%></option>
				
				<%
				}
			%>
		</select>
		<select id="cums" name="cums">
			<option value="" style="color:grey">Secondo set di valori</option>
			<%
			for(UnitaMisuraDTO um : listUM){
				%>
				
				<option value="<%=um.getNome()%>"><%=um.getNome()%></option>
				
				<%
				}
			%>
		</select>
		<input name="mode" value="insert" style="display:none;"/>
		<button type="submit">Crea</button>
	</form>
	</div>
	<%
if(request.getAttribute("err")!=null){
	%>
	<div id="err" class="msgerr">
	<label class="closeerr" onclick="chiudiParente(this)">[X] Chiudi</label>
	<%
	switch (Integer.parseInt(request.getAttribute("err").toString())){
		case 1:
		%>
			<strong>Impossibile creare nuovo:</strong> DataSet esistente!<br>Si consiglia di modificare quello esistente.
		<%
		break;
	}
	%>
	</div>
	<%
}
%>
</body>

<script>
	window.addEventListener('resize',resize);
	resize();
</script>

</html>
