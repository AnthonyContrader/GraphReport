<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UnitaMisuraDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/vittoriostyle.css" rel="stylesheet">
<title>Read UnitaMisura</title>
</head>
<body>

<div class="main">
<%UnitaMisuraDTO m = (UnitaMisuraDTO) request.getAttribute("dto");%>


<table>
	<tr> 
		<th>Nome</th>
		
	</tr>
	<tr>
		<td><%=m.getNome()%></td>
		
	</tr>	
</table>


</div>


</body>
</html>