<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UnitaMisuraDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="Stylesheet" href="css/vittoriostyle.css" />

</head>
<body>
<div class = "main">
	<%
		List<UnitaMisuraDTO> list = (List <UnitaMisuraDTO>) request.getAttribute("list");
	%>
	<br>
	
		<table>
			<tr>
				<th>Nome</th>
				<th></th>
				<th></th>
			</tr>
			<%
				for (UnitaMisuraDTO m : list) {
			%>
			<tr>
			<td><a href=UnitaMisuraServlet?mode=read&id=<%=m.getId()%>>
					<%=m.getNome()%>
			</a></td>
			<td><a href=UnitaMisuraServlet?mode=read&update=true&id=<%=m.getId()%>>Edit</a>
			</td>
			<td><a href=UnitaMisuraServlet?mode=delete&id=<%=m.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
		</table>
		
<form id="floatright" action="UnitaMisuraServlet?mode=insert" method="post">
  <div class="row">
    <div class="col-25">
      <label for="user">Nome</label>
    </div>
    <div class="col-75">
      <input type="text" id="unitamisura" name="nome" placeholder="inserisci nome">
    </div>
  </div>
      <button type="submit" >Insert</button>
</form>
		
		

</div>
</body>
</html>