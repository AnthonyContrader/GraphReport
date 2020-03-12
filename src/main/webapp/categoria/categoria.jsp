<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.CategoriaDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="Stylesheet" href="css/vittoriostyle.css" />

</head>
<body>
<div class = "main">
	<%
		List<CategoriaDTO> list = (List <CategoriaDTO>) request.getAttribute("list");
	%>
	<br>
	
		<table>
			<tr>
				<th>Nome</th>
				<th></th>
				<th></th>
			</tr>
			<%
				for (CategoriaDTO c : list) {
			%>
			<tr>
			<td><a href=CategoriaServlet?mode=read&id=<%=c.getId()%>>
					<%=c.getNome()%>
			</a></td>
			<td><a href=CategoriaServlet?mode=read&update=true&id=<%=c.getId()%>>Edit</a>
			</td>
			<td><a href=CategoriaServlet?mode=delete&id=<%=c.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
		</table>
		
<form id="floatright" action="CategoriaServlet?mode=insert" method="post">
  <div class="row">
    <div class="col-25">
      <label for="user">Nome</label>
    </div>
    <div class="col-75">
      <input type="text" id="categoria" name="nome" placeholder="inserisci nome">
    </div>
  </div>
      <button type="submit" >Insert</button>
</form>
		
		

</div>
</body>
</html>