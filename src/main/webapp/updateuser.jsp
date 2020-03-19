<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="User Edit page">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Edit User</title>

</head>
<body>
<br>
<div class="main">

<%UserDTO u = (UserDTO) request.getAttribute("dto");%>


<form id="floatleft" action="/user/update" method="post">
  <input type="text" id="user" name="id" value=<%=u.getId()%> style="display:none">
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
  				<option value="ADMIN" <%if(u.getUsertype().toString().equalsIgnoreCase("admin")) {%>selected<%}%>>ADMIN</option>
  				<option value="USER"  <%if(u.getUsertype().toString().equalsIgnoreCase("user")) {%>selected<%}%>>USER</option>
			</select>
    	</div>
  </div>
  <%if(request.getSession().getAttribute("usertype").toString().equalsIgnoreCase("user") || 
		  u.getId()==Long.parseLong(request.getSession().getAttribute("userid").toString())){ %>
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
      <label for="user">Citt�</label>
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
  <%} %>
      <button type="submit" >Edit</button>
</form>

	
</div>
<br>
</body>
</html>