<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Graph</title>
<script type="text/javascript" src="../js/canvasjs.min.js"></script>
<link href="../css/toniostyle.css" rel="stylesheet">
</head>
<body>
QUI TANTA PIU' ROBA DI LI'
<br><br>
INTANTO 
<button onclick="drawGraph()" style="width:200px;height:30px;padding:0px;margin:5px">CLICCA QUI </button>
<div id="graphView" style="width:100%;height:300px"></div>

</body>
<script>

function drawGraph(){
	
	var graph = new CanvasJS.Chart("graphView", {
		title:{
			text: "CATEGORIA"
		},
		data:[
				{
				type: "column",
				dataPoints : [ 
						{ x: <%= request.getAttribute("x")%> , y: <%= request.getAttribute("y")%>,  indexLabel: "<%= request.getAttribute("label")%>"  }	
					]
				}
			]
	});
	
	graph.render();
	
}
</script>


</html>

