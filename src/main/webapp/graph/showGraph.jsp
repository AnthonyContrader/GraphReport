<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.GraphDTO" import="it.contrader.model.Graph.TipoGrafico" import="it.contrader.model.Graph.Tema"
    import="it.contrader.dto.DataSetDTO" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Graph</title>
<script type="text/javascript" src="../js/canvasjs.min.js"></script>
<script type="text/javascript" src="../js/toniojs.js"></script>
<link href="../css/toniostyle.css" rel="stylesheet">
</head>
<body>

<%
String categorie="", unitM="", jsArrayListUM = "[";
if(request.getAttribute("mode").toString().equalsIgnoreCase("up")){
	List<GraphDTO> list = (List<GraphDTO>) request.getAttribute("listGraph");
	List<DataSetDTO> listDS = (List<DataSetDTO>) request.getAttribute("listC");
	
	Long[] noDuplicate = new Long[listDS.size()];
	int i;
	Long id=Long.parseLong(request.getSession().getAttribute("userid").toString());
	boolean unique;
	
	//GENERA LISTA CATEGORIE PRIVA DI DUPLICATI E LISTA UNITAMISURA PER IL JS
	for(DataSetDTO ds : listDS){
		jsArrayListUM+="\""+ds.getCategoria()+"§"+ds.getId()+"§"+ds.getUnitaMisuraN()+" "+ds.getCommento()+"\",";
		i=0;
		unique=true;
		while((i<noDuplicate.length)&& unique){
			if(noDuplicate[i]!=ds.getCategoria()){
				if(noDuplicate[i]==null){
					noDuplicate[i]=ds.getCategoria();
					categorie+="<option value=\""+ds.getCategoria()+"\">"+ds.getCategoriaN()+"</option>";
					unique=false;
					}
			}else
				unique=false;
			i++;
		}
		
	}
	jsArrayListUM=jsArrayListUM.substring(0,jsArrayListUM.length() - 1) + "]";	
}

	GraphDTO dto = (GraphDTO) request.getAttribute("graph");
	List<String> arrayValue = (List<String>) request.getAttribute("arrV");
	List<String> tagY = (List<String>) request.getAttribute("tagY");
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
				
				<input type="checkbox" class="checkbox" onchange="document.getElementById('leg').value=this.checked" <% if(dto.getLegenda()){%> checked <% } %>><strong>Legenda</strong>
				<input id="leg" name="legenda" value="<%= dto.getLegenda() %>" style="display:none">
				
				<br>
				
				<input type="checkbox" class="checkbox" onchange="document.getElementById('zm').value=this.checked" <% if(dto.getZoom()){%> checked <% } %>><strong>Zoom</strong>
				<input id="zm" name="zoom" value="<%= dto.getZoom() %>" style="display:none">
				
				<br>
				
				<input type="checkbox" class="checkbox" onchange="document.getElementById('par').value=this.checked" <% if(dto.getPareto()){%> checked <% } %>><strong>Pareto</strong>
				<input id="par" name="pareto" value="<%= dto.getPareto() %>" style="display:none">
				
				<input name="mode" value="<%= request.getAttribute("mode").toString() %>" style="display:none">
				<input name="id" value="<%= request.getAttribute("id").toString() %>" style="display:none">
				<button type="submit">Salva</button>
			</form>
		</div>
	<% } %>
<div class="cols">
	<div id="graphView"></div>
</div>
<% if(request.getAttribute("mode").toString().equalsIgnoreCase("up")){ %>
<div class="cols" style="width:250px;float:right">
	<form action="addset" method="post" style="width:200px">
		<label><strong>Aggiungi Set di valori</strong></label>
		<div class="contenitor">
			<select id="cat" onchange="popolaAssi()" required>
				<option value="" style="color:grey">DataSet</option>
				<%= categorie %>
			</select>
			<select id="assex" name="assex" disabled required>
				<option value="" style="color:grey">Asse X</option>
			</select>
			<select id="assey" name="assey" disabled required>
				<option value="" style="color:grey">Asse Y</option>
			</select>
		</div>
		<input name="mode" value="<%= request.getAttribute("mode").toString() %>" style="display:none">
		<input name="id" value="<%= request.getAttribute("id").toString() %>" style="display:none">
		<button type="submit">Aggiungi</button>
	</form>
</div>
<% } %>
</body>
<script>
var chart;
function drawGraph(){
	chart = new CanvasJS.Chart("graphView", {
		theme: "<%= dto.getTema().toString() %>",
		animationEnabled: true,
		exportEnabled: true,
		zoomEnabled: <%= dto.getZoom() %>, 
		title:{
			text: "<%= dto.getTitolo() %>",
			verticalAlign: "<%= dto.getPosTitolo().split("_")[0] %>", 
	        horizontalAlign: "<%= dto.getPosTitolo().split("_")[1] %>"
		},
		legend:{
			cursor: "pointer",
			fontSize: 16,
			itemclick: toggleDataSeries
		},
		data:[
		<%
		int z=0;
		for(String s : arrayValue){ %>
			
					{
					type: "<%= dto.getTipografico().toString() %>",
					showInLegend: <%= dto.getLegenda() %>,
					<% if(arrayValue.size()>1) {%>
						legendText: "<%= tagY.get(z) %>",
						<% } %>
					dataPoints : [ <%= s %> ]
					},
				
		<% 
		z++;
		} 
		%>
		]
		});
		
	
	chart.render();
	<% if(dto.getPareto()) { %>
		pareto();
		<% } %>
	
	function toggleDataSeries(e){
		if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
			e.dataSeries.visible = false;
		}
		else{
			e.dataSeries.visible = true;
		}
		chart.render();
	}

}

function pareto(){
	
	var dps = [];
	var yValue, yTotal = 0, yPercent = 0;

	for(var i = 0; i < chart.data[0].dataPoints.length; i++)
		yTotal += chart.data[0].dataPoints[i].y;

	for(var i = 0; i < chart.data[0].dataPoints.length; i++){
		yValue = chart.data[0].dataPoints[i].y;
		yPercent += (yValue / yTotal * 100);
		dps.push({label: chart.data[0].dataPoints[i].label, y: yPercent});
	}
	
	chart.addTo("data",{type:"line", yValueFormatString: "0.##\"%\"", dataPoints: dps});
	chart.data[1].set("axisYType", "secondary", false);
	chart.axisY[0].set("maximum", yTotal);
	chart.axisY2[0].set("maximum", 100);

	}	

<% if(request.getAttribute("mode").toString().equalsIgnoreCase("up")){ %>
function popolaAssi(){
	var list = <%= jsArrayListUM %>, 
		cat=document.getElementById('cat').value
		splitted="",html="";
	if(cat!=""){
		for(var i=0;i<list.length;i++){
			splitted=list[i].split("§");
			if(splitted[0]==cat)
				html+='<option value="'+splitted[1]+'">'+splitted[2]+'</option>';
		}
		document.getElementById("assex").innerHTML='<option value="" style="color:grey">Asse X</option>'+html;
		document.getElementById("assey").innerHTML='<option value="" style="color:grey">Asse Y</option>'+html;
		
		document.getElementById("assex").removeAttribute('disabled');
		document.getElementById("assey").removeAttribute('disabled');
	}else{
		document.getElementById("assex").innerHTML='<option value="" style="color:grey">Asse X</option>';
		document.getElementById("assey").innerHTML='<option value="" style="color:grey">Asse Y</option>';
		
		document.getElementById("assex").setAttribute('disabled','disabled');
		document.getElementById("assey").setAttribute('disabled','disabled');
	}
}
<% } %>

 window.addEventListener("load", drawGraph);
	
</script>


</html>

