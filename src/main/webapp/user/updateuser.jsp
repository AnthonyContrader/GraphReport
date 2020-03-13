<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/vittoriostyle.css" rel="stylesheet">
<title>Edit User</title>
</head>
<body>

<div class="main">

<%UserDTO u = (UserDTO) request.getAttribute("dto");%>


<form id="floatleft" action="UserServlet?mode=update&id=<%=u.getId()%>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="user">Username</label>
    </div>
    <div class="col-75">
      <input type="text" id="user" name="username" value=<%=u.getUsername()%>>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="pass">Password</label>
    </div>
    <div class="col-75">
      <input
			type="text" id="pass" name="password" value=<%=u.getPassword()%>> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="type">Usertype</label>
    </div>
   		 <div class="col-75">
 			<select id="type" name="usertype">
  				<option value="ADMIN" <%if(u.getUsertype().equals("ADMIN")) {%>selected<%}%>>ADMIN</option>
  				<option value="USER" <%if(u.getUsertype().equals("USER")) {%>selected<%}%>>USER</option>
			</select>
    	</div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="user">Nome</label>
    </div>
    <div class="col-75">
      <input type="text" id="nome" name="nome" value=<%=u.getNome()%>>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="user">Cognome</label>
    </div>
    <div class="col-75">
      <input type="text" id="cognome" name="cognome" value=<%=u.getCognome()%>>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="user">Email</label>
    </div>
    <div class="col-75">
      <input type="text" id="email" name="email" value=<%=u.getEmail()%>>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="user">Città</label>
    </div>
    <div class="col-75">
      <input type="text" id="citta" name="citta" value=<%=u.getCitta()%>>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="user">Nazione</label>
    </div>
    <div class="col-75">
      <input type="text" id="nazione" name="nazione" value=<%=u.getNazione()%>>
    </div>
  </div>
      <button type="submit" >Edit</button>
</form>

	
</div>

</body>
</html>