<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.GraphDTO" import="it.contrader.model.Graph.TipoGrafico" import="it.contrader.model.Graph.Tema"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Graph</title>
<script type="text/javascript" src="../js/canvasjs.min.js"></script>
<link href="../css/toniostyle.css" rel="stylesheet">
</head>
<body>

<%
	GraphDTO dto = (GraphDTO) request.getAttribute("graph");
	String arrayValue = request.getAttribute("arrV").toString();
	String[] posTitolo=dto.getPosTitolo().split("_");

	if(request.getAttribute("mode").toString().equalsIgnoreCase("up")){
%>
		<div class="cols" style="width:250px;">
			<form action="update" method="post" style="width:200px">
				
				<strong>Titolo</strong>
				<input type="text" name="titolo" value="<%= dto.getTitolo() %>" placeholder="Inserisci un titolo" style="float:none" required/>
				
				<strong>Altezza Titolo</strong>
				<select name="altTitolo">
					<option  value="top" <%if(posTitolo[0].equalsIgnoreCase("top")) {%> selected <%}%>>In alto</option>
					<option  value="center" <%if(posTitolo[0].equalsIgnoreCase("center")) {%> selected <%}%>>Al centro</option>
					<option  value="bottom" <%if(posTitolo[0].equalsIgnoreCase("bottom")) {%> selected <%}%>>In basso</option>
				</select>
				
				<strong>Posizione Titolo</strong>
				<select name="posTitolo">
					<option  value="left" <%if(posTitolo[1].equalsIgnoreCase("left")) {%> selected <%}%>>A sinistra</option>
					<option  value="center" <%if(posTitolo[1].equalsIgnoreCase("center")) {%> selected <%}%>>Al centro</option>
					<option  value="right" <%if(posTitolo[1].equalsIgnoreCase("right")) {%> selected <%}%>>A destra</option>
				</select>
				
				<strong>Tipologia grafico</strong>
				<select name="tipo">
				<%	
					for(TipoGrafico t : TipoGrafico.values()){
						%>
						<option  value="<%= t.toString() %>" <%if(t.toString().equalsIgnoreCase(dto.getTipografico().toString())) {%> selected <%}%>><%=t.toString()%></option>
						<%
					}
					%>
				</select>
				
				<strong>Tema</strong>
				<select name="tema">
				<%	
					for(Tema t : Tema.values()){
						%>
						<option  value="<%= t.toString() %>" <%if(t.toString().equalsIgnoreCase(dto.getTema().toString())) {%> selected <%}%>><%=t.toString()%></option>
						<%
					}
					%>
				</select>
				
				<input type="checkbox" class="checkbox" onchange="document.getElementById('leg').value=this.checked"><strong>Legenda</strong>
				<input id="leg" name="legenda" value="<%= dto.getLegenda() %>>" style="display:none">
				
				<br>
				
				<input type="checkbox" class="checkbox" onchange="document.getElementById('zm').value=this.checked"><strong>Zoom</strong>
				<input id="zm" name="zoom" value="<%= dto.getZoom() %>>" style="display:none">
				
				<br>
				
				<input type="checkbox" class="checkbox" onchange="document.getElementById('par').value=this.checked"><strong>Pareto</strong>
				<input id="par" name="pareto" value="<%= dto.getPareto() %>>" style="display:none">
				
				<button type="submit">Salva</button>
			</form>
		</div>
	<% } %>
<div class="cols divGraph">
	<div id="graphView" style="width:100%;"></div>
</div>
</body>
<script>
window.addEventListener("load", drawGraph);

function drawGraph(){
	var graph = new CanvasJS.Chart("graphView", {
		theme: "<%= dto.getTema().toString() %>",
		animationEnabled: true,
		exportEnabled: true,
		title:{
			text: "<%= dto.getTitolo() %>"
		},
		axisX: {
			title: "<%= request.getAttribute("titleX") %>"
		},
		axisY: {
			title: "<%= request.getAttribute("titleY") %>"
		},
		data:[
				{
				type: "<%= dto.getTipografico().toString() %>",
				dataPoints : [ <%= arrayValue %> ]
				}
			]
		});
	
	graph.render();
	
	}
</script>


</html>

