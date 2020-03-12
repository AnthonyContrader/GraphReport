<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.CategoriaDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/vittoriostyle.css" rel="stylesheet">
<title>Read Categoria</title>
</head>
<body>

<div class="main">
<%CategoriaDTO c = (CategoriaDTO) request.getAttribute("dto");%>


<table>
	<tr> 
		<th>Nome</th>
		
	</tr>
	<tr>
		<td><%=c.getNome()%></td>
		
	</tr>	
</table>


</div>


</body>
</html>