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

	<form action="UserServlet?mode=insert" method="post">
			<div class="row">
				<div class="col-25">
					<label for="user">Username</label>
				</div>
				<div class="col-75">
					<input type="text" id="user" name="username"
						placeholder="inserisci username">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="pass">Password</label>
				</div>
				<div class="col-75">
					<input type="text" id="pass" name="password"
						placeholder="inserisci password">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="type">Usertype</label>
				</div>
				<div class="col-75">
					<select id="type" name="usertype">
						<option value="ADMIN">ADMIN</option>
						<option value="USER">USER</option>

					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="user">Nome</label>
				</div>
				<div class="col-75">
					<input type="text" id="nome" name="nome"
						placeholder="inserisci nome">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="user">Cognome</label>
				</div>
				<div class="col-75">
					<input type="text" id="cognome" name="cognome"
						placeholder="inserisci cognome">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="user">Email</label>
				</div>
				<div class="col-75">
					<input type="text" id="email" name="email"
						placeholder="inserisci email">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="user">Città</label>
				</div>
				<div class="col-75">
					<input type="text" id="citta" name="citta"
						placeholder="inserisci città">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="user">Nazione</label>
				</div>
				<div class="col-75">
					<input type="text" id="nazione" name="nazione"
						placeholder="inserisci nazione">
				</div>
			</div>
			<button type="submit">Insert</button>
	</form>
</div>
</body>
</html>