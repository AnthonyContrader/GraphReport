<%@ page import="it.contrader.dto.UnitaMisuraDTO" import="java.util.*" %>
<html>
<head>
<script type="text/javascript" src="../js/toniojs.js"></script>
<script type="text/javascript" src="../js/piero.js"></script>
<meta charset="utf-8">
<meta name="viewport" 
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Unita Misura Home">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Unita Misura Home </title>
</head>
<body>
	<div class="main">
	<%
		List<UnitaMisuraDTO> list = (List<UnitaMisuraDTO>) request.getAttribute("list");
	
	%>
	<br>
	
	<table>
		<tr>
			<th>Unita di Misura</th>
			<%if(request.getSession().getAttribute("usertype").toString().equalsIgnoreCase("admin")){ %>
			<th></th>
			<th></th>
			<%} %>
		</tr>
		<%
			for(UnitaMisuraDTO m : list){
		%>
		<tr>
			<td><%=m.getNome()%></td>
			<%if(request.getSession().getAttribute("usertype").toString().equalsIgnoreCase("admin")){ %>
			<td><a href="/unitamisura/preupdate?id=<%=m.getId()%>">Edit</a></td>
			<td><a href="/unitamisura/delete?id=<%=m.getId()%>">Delete</a></td>
			<%} %>
		</tr>
		
		<%
			}
		%>
	</table>
	
	<form id="floatright" action="/unitamisura/insert" onsubmit="return conferma('unitamisura',true)" method="post">
		<div class="row">
			<div class="col-25">
				<label for="user">Nome</label>
			</div>
			<div class="col-75">
			<input type="text" id="unitamisura" name="nome"
				placeholder="inserisci testo qui">
			</div>
		</div>
		<button type="submit">Aggiungi Unita di Misura</button>
	</form>
	
	</div>

</body>
<script>
	<% if(request.getAttribute("err")!=null){
	switch (Integer.parseInt(request.getAttribute("err").toString())){
		case 1:
			%>
			alert("Impossibile eliminare un'unità di misura utilizzata da altri utenti");
			<%
		break;
	}
	}
	%>
</script>
</html>