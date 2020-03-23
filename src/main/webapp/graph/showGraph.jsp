<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.GraphDTO"%>
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
%>

<div id="graphView" style="width:100%;height:300px"></div>

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

