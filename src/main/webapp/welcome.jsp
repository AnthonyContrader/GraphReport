<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link href="css/vittoriostyle.css" rel="stylesheet">
</head>
<body class="main">
<% UserDTO user = (UserDTO) session.getAttribute("dto"); %>
<h1>Welcome <%= user.getUsername()%></h1>

QUI TANTA ROBA

</body>
</html>