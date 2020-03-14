<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.DataSetDTO" import="it.contrader.dto.UnitaMisuraDTO" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/toniostyle.css" rel="stylesheet">
<script type="text/javascript" src="js/toniojs.js"></script>
</head>
<body>

<%
if(request.getAttribute("err")!=null){
	%>
	<div id="err">
	<%
	switch (Integer.parseInt(request.getAttribute("err").toString())){
		case 1:
			%>
			<strong>Impossibile Aggiungere:</strong> unità di misura esistente!<br>Si consiglia di modificare quella esistente.
			<%
			break;
		case 2:
			%>
			<strong>Errore imprevito!</strong><br>Impossibile eliminare la colonna selezionata.
			<%
			break;
		case 3:
			%>
			<strong>Errore imprevito!</strong><br>Impossibile memorizzare i nuovi dati.
			<%
			break;
	}
	%>
	</div>
	<%
}
%>

	<div class="cols half">
	
<%
		List<DataSetDTO> list = (List <DataSetDTO>) request.getAttribute("dataset");
		List<UnitaMisuraDTO> listUM = (List <UnitaMisuraDTO>) request.getAttribute("listUnit");
		String cat = "";
		boolean first=true;
		int grey=0,n=0;
		String delOption="";
		
		for (DataSetDTO ds : list){
			if(first){
				cat=ds.getCategoria();
				%>
				<form class="noForm" action="DataSetServlet" id="formDSUpdate">
					<input name="mode" value="update" style="display:none;"/>
					<input name="cat" value="<%= cat %>" style="display:none;"/>
				</form>
				<div class="newCat">
					<h1><%= cat %></h1>
					<div class="center">
				<%
				first=false;
			}
			String[] valori = ds.getValore().split("_");
			delOption+="<option value=\""+ds.getId()+"\">"+ds.getUnitaMisura()+"</option>";
			n=valori.length;
			%>
			<div name="um" class="cols marginBot">
				<div class="th">
					<%= ds.getUnitaMisura() %>
				</div>
				<%
				grey=0;
				for(int i=0;i<n;i++){
				%>
					<div name="row<%= i+1 %>" class="tr <% if(grey%2==0){ %>grey<% } %>">
					<input type="text" class="tableInput" value="<%= valori[i] %>" onchange="activeSave()"/>
					</div>
				<%
					grey++;
				} 
				%>
				<div class="tr <% if(grey%2==0){ %>grey<% } %>">
					<input type="text" class="tableInput" value="" disabled/>
				</div>
			</div>
			
		<%
		}
		%>
		<div class="cols marginBot" name="um">
		<div class="th" style="height:18px;width:40px;">
			
		</div>
		<%
		grey=0;
		for(int i=0;i<=n;i++){
			 if(i==n){ %>
				<div class="tr <% if(grey%2==0){ %>grey<% } %>" style="width:40px;"> 
					<img src="img/add.png" title="Crea nuova riga" onclick="addRow()"/>
				<% }else{ %> 
				<div name="row<%= i+1 %>" class="tr <% if(grey%2==0){ %>grey<% } %>" style="width:40px;">
					<img src="img/remove.png" title="Elimina riga" onclick="delRow(<%= i+1 %>)"/>
				 <% } %>
			</div>
		<%
			grey++;
		} 
		%>
	</div>
		
		</div></div>
	</div>	
	<div class="cols half">
	
	<h1>Colonne</h1>
	<form class="center" action="DataSetServlet" style="min-width:270px;margin-bottom:15px;" onsubmit="return checkColonna('ci')">
		<label><strong>Aggiungi nuova:</strong></label>
		<select id="ci" name="unit">
			<option value=""></option>
			<%
			for(UnitaMisuraDTO um : listUM){
				%>
				
				<option value="<%=um.getNome()%>"><%=um.getNome()%></option>
				
				<%
				}
			%>
		</select>
		<input name="mode" value="insertrow" style="display:none;"/>
		<input name="n" value="<%=n %>" style="display:none;"/>
		<input name="cat" value="<%=cat %>" style="display:none;"/>
		<button type="submit">Aggiungi</button>
	</form>
	<form class="center" action="DataSetServlet" style="min-width:270px" onsubmit="return checkColonna('ce',true)">
		<label><strong>Elimina:</strong></label>
		<select id="ce" name="id">
			<option value=""></option>
			<%= delOption %>
		</select>
		<input name="mode" value="deleterow" style="display:none;"/>
		<input name="cat" value="<%= cat %>" style="display:none;"/>
		<button type="submit">Elimina</button>
	</form>
	</div>
	<div class="btnApply">
		<button onclick="submitUpdate()" id="btnSalva" disabled>Salva modifiche</button>
	</div>
</body>
</html>