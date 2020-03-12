<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/vittoriostyle.css" rel="stylesheet">
<title>Read User</title>
</head>
<body>

<div class="main">
<%UserDTO u = (UserDTO) request.getAttribute("dto");%>


<table>
	<tr> 
		<th>Username</th>
		<th>Password</th>
		<th>Usertype</th>
	</tr>
	<tr>
		<td><%=u.getUsername()%></td>
		<td> <%=u.getPassword()%></td>
		<td> <%=u.getUsertype()%></td>
	</tr>	
</table>


</div>


</body>
</html>