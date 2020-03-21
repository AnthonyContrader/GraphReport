<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.DataSetDTO" import="it.contrader.dto.UserDTO" import="it.contrader.dto.CategoriaDTO" import="it.contrader.dto.UnitaMisuraDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DataSet</title>
<link href="../css/toniostyle.css" rel="stylesheet">
<script type="text/javascript" src="../js/toniojs.js"></script>
</head>
<body>
	<div class="cols half">
<%
		List<DataSetDTO> list = (List <DataSetDTO>) request.getAttribute("list");
		List<CategoriaDTO> listC = (List <CategoriaDTO>) request.getAttribute("listCat");
		List<UnitaMisuraDTO> listUM = (List <UnitaMisuraDTO>) request.getAttribute("listUni");
		Long cat = null;
		boolean first=true;
		
		if(session.getAttribute("usertype").toString().equalsIgnoreCase("admin")){
			List<UserDTO> listU = (List <UserDTO>) request.getAttribute("listUtente");
			%>
			<form class="center" id="visDataSetUt" action="viewUt" style="width:max-content" method="post">
			<label>Visualizza DataSet dell'utente</label>
			<select name="idUtVis" onchange="document.getElementById('visDataSetUt').submit();" style="width:max-content">
				<option value="<%=session.getAttribute("userid")%>">Tuoi Personali</option>
				<%
				for(UserDTO u : listU){
					if(u.getId()!=Long.parseLong(session.getAttribute("userid").toString())){
					%>
					
					<option value="<%=u.getId()%>" <% if(request.getAttribute("idUtVis")!=null){if(Long.parseLong(request.getAttribute("idUtVis").toString())==u.getId()){ %>selected<% } } %>><%=u.getUsername()%></option>
					
					<%
					}
				}
				%>
				</select>
			</form>
			<%
		}
		if(!list.isEmpty()){
			for (DataSetDTO ds : list){
				if(cat!=ds.getCategoria()){
					if(!first){
						%>
						</div>
						</div>
						<%
					}else first=false;
					cat=ds.getCategoria();
				%>
				<div class="newCat">
					<% if(ds.getUtente()==Long.parseLong(session.getAttribute("userid").toString())){ %>
					<div class="divCRUD" style="width:20%;">
						<a href="read?id=<%=cat%>" ><div class="linkCRUD">Modifica</div></a>
						<a href="deletedataset?id=<%=cat%>" onclick="return confDelDS()"><div class="linkCRUD">Elimina</div></a>
					</div>
					<% } %>
				<h1><%= ds.getCategoriaN() %></h1>
				<div class="center">
				<% }
				String[] valori = ds.getValore().split("_");
				%>
					<div class="cols marginBot">
						<div class="th">
							<%= ds.getUnitaMisuraN() %>
							<% if (!ds.getCommento().equals("")){ %>
								<div class="btnComm tooltip">
									<img src="../img/comm.png"/>
									<span class="tooltiptext">
										<p> <%= ds.getCommento() %> </p>
									</span>
								</div>
							<% } %>
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
		}else{
		%>
		<h1 style="margin-top:80px;">Nessun DataSet presente!</h1>
		<%} %>
		</div></div>
	</div>	
	<div class="cols half">
	<h1>Crea nuovo DataSet</h1>
	<p class="center">In seguito alla creazione sarà possibile inserire nuovi valori e nuove tipologie di valori.</p>
	<form class="center" action="createdataset" style="min-width:270px" method="post">
		<label><strong>Categoria</strong></label>
		<select id="cc" name="cat" required>
			<option value="" style="color:grey">Scegli una categoria</option>
			<%
			for(CategoriaDTO c : listC){
				%>
				
				<option value="<%=c.getId()%>]<%=c.getNome()%>"><%=c.getNome()%></option>
				
				<%
				}
			%>
		</select>
		<label><strong>Unità di misura:</strong></label>
		<br>
		<select id="cump" name="ump" required>
			<option value="" style="color:grey">Primo set di valori</option>
			<%
			for(UnitaMisuraDTO um : listUM){
				%>
				
				<option value="<%=um.getId()%>]<%=um.getNome() %>"><%=um.getNome()%></option>
				
				<%
				}
			%>
		</select>
		<select id="cums" name="ums" required>
			<option value="" style="color:grey">Secondo set di valori</option>
			<%
			for(UnitaMisuraDTO um : listUM){
				%>
				
				<option value="<%=um.getId()%>]<%=um.getNome()%>"><%=um.getNome()%></option>
				
				<%
				}
			%>
		</select>
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
