<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.CategoriaDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/vittoriostyle.css" rel="stylesheet">
<title>Edit Categoria</title>
</head>
<body>

<div class="main">

<%CategoriaDTO c = (CategoriaDTO) request.getAttribute("dto");%>


<form id="floatleft" action="CategoriaServlet?mode=update&id=<%=c.getId()%>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="user">Nome</label>
    </div>
    <div class="col-75">
      <input type="text" id="categoria" name="nome" value=<%=c.getNome()%>>
    </div>
  </div>
      <button type="submit" >Edit</button>
</form>

	
</div>

</body>
</html>