<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GraphReport</title>
<link href="css/vittoriostyle.css" rel="stylesheet">
<script type="text/javascript" src="js/javascript.js"></script>
</head>
<body style="min-width:760px">
<%@include file="css/header.jsp"%>


<div class="navbar" id="navbar">
  <a class="active" href="homeStructure.jsp" onclick="setActive(0)">Home</a>
  <a href="UserServlet?mode=list" target="frameBody" onclick="setActive(1)">Utente</a>
  <a href="DataSetServlet?mode=list" target="frameBody" onclick="setActive(2)">DataSet</a>
  <a href="CategoriaServlet?mode=list" target="frameBody" onclick="setActive(3)">Categoria</a>
  <a href="UnitaMisuraServlet?mode=list" target="frameBody" onclick="setActive(4)">Unità di misura</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>

<iframe class="iframeinvisible" src="welcome.jsp" name="frameBody">


</iframe>


<%@ include file="css/footer.jsp" %>

</body>
</html>