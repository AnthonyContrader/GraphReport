<%@ page import="it.contrader.dto.CategoriaDTO" import="java.util.*" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" 
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Categoria Home">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<script type="text/javascript" src=../js/riccardo.js></script>
<title>Categoria Home </title>
</head>
<body>
	<div class="main">
	<%
		List<CategoriaDTO> list = (List<CategoriaDTO>) request.getAttribute("list");
	
	%>
	<br>
	
	<table>
		<tr>
			<th>Lista Categorie</th>
			<%if(request.getSession().getAttribute("usertype").toString().equalsIgnoreCase("admin")){ %>
			<th></th>
			<th></th>
			<%} %>
		</tr>
		<%
			for(CategoriaDTO c : list){
		%>
		<tr>
			<td> <%=c.getNome()%></td>
				<%if(request.getSession().getAttribute("usertype").toString().equalsIgnoreCase("admin")){ %>
			<td><a href="/categoria/preupdate?id=<%=c.getId()%>">Edit</a></td>
			
			<td><a href="/categoria/delete?id=<%=c.getId()%>">Delete</a></td>
			<% } %>
		</tr>
		
		<%
			}
		%>
	</table>
	
	<form id="floatright" action="/categoria/insert" onsubmit="return conferma('categoria',true)" method="post">
		<div class="row">
			<div class="col-25">
				<label for="user">Agg.Categoria</label>
			</div>
			<div class="col-75">
			<input type="text" id="categoria" name="nome"
				placeholder="inserire testo qui">
			</div>
		</div>
		<button type="submit">Aggiungi Categoria</button>
	</form>
	
	</div>

</body>

<script>
<% if(request.getAttribute("err")!= null){
	switch(Integer.parseInt(request.getAttribute("err").toString())){
	case 1:
	%>
	//Messaggio in javascript
	alert("La categoria è in utilizzo!\n\n Impossibile eliminare!");
	
	<%
	break;
	case 2:
		%>
		alert("La categoria è gia esistente!")
		<%
		break;
	}
}
%>
</script>
</html>