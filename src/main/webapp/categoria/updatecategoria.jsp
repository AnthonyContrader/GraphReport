<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.CategoriaDTO"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Categoria Edit">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Categoria Edit</title>
</head>
<body>

<div class = "main">
	<%
		CategoriaDTO c = (CategoriaDTO) request.getAttribute("dto");
	%>
	
<form id="floatleft" action="/categoria/update" onsubmit="return conferma(categoria,false)"  method="post">
	<input type="text" name="id" value=<%=c.getId()%> style="display:none">
	<div class="row">
		<div class="col-25">
			<label for="categoria">Nome</label>
		</div>
		<div class="col-75">
			<input type="text" id="categoria" name="nome" value=<%=c.getNome()%>>
		</div>
	</div>
		<button type="submit">Edit</button>

</form>
</div>
</body>
</html>