<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UnitaMisuraDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/vittoriostyle.css" rel="stylesheet">
<title>Edit UnitaMisura</title>
</head>
<body>

<div class="main">

<%UnitaMisuraDTO m = (UnitaMisuraDTO) request.getAttribute("dto");%>


<form id="floatleft" action="UnitaMisuraServlet?mode=update&id=<%=m.getId()%>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="user">Nome</label>
    </div>
    <div class="col-75">
      <input type="text" id="unitamisura" name="nome" value=<%=m.getNome()%>>
    </div>
  </div>
      <button type="submit" >Edit</button>
</form>

	
</div>

</body>
</html>