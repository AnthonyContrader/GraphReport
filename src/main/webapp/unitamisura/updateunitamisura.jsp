<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UnitaMisuraDTO"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Unita Misura Edit">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Unita Misura Edit</title>
</head>
<body>

<div class = "main">
	<%
		UnitaMisuraDTO m = (UnitaMisuraDTO) request.getAttribute("dto");
	%>
	
<form id="floatleft" action="/unitamisura/update" onsubmit="return conferma('unitamisura',false)" method="post">
	<input type="text" name="id" value=<%=m.getId()%> style="display:none">
	<div class="row">
		<div class="col-25">
			<label for="unitamisura">Nome</label>
		</div>
		<div class="col-75">
			<input type="text" id="unitamisura" name="nome" value=<%=m.getNome()%>>
		</div>
	</div>
		<button type="submit">Edit</button>

</form>
</div>
</body>
</html>