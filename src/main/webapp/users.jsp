<%@ page import="it.contrader.dto.UserDTO" import="java.util.*"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="User Management">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>User Manager</title>

</head>
<body>
	<div class="main">
		<%
			List<UserDTO> list=null;
			UserDTO p=null;
			if(request.getSession().getAttribute("usertype").toString().equalsIgnoreCase("admin")){
			list = (List<UserDTO>) request.getAttribute("list");
			}
			else{
			p = (UserDTO) request.getAttribute("user");
			}
		%>

		<br>

		<table>
			<tr>
				<th>Username</th>
				<th>Password</th>
				<th>Usertype</th>
				<%if(request.getSession().getAttribute("usertype").toString().equalsIgnoreCase("user")){%>
				<th>Nome</th>
				<th>Cognome</th>
				<th>Email</th>
				<th>Città</th>
				<th>Nazione</th>
				<%}%>
				<th></th>
				<th></th>
			</tr>
			<%
			if(request.getSession().getAttribute("usertype").toString().equalsIgnoreCase("admin")){
				for (UserDTO u : list) {
			%>
			<tr>
				<td><a href="/user/read?id=<%=u.getId()%>"> <%=u.getUsername()%>
				</a></td>
				<td><%=u.getPassword()%></td>
				<td><%=u.getUsertype()%></td>
				<td><a href="/user/preupdate?id=<%=u.getId()%>">Edit</a></td>
				<td><a href="/user/delete?id=<%=u.getId()%>">Delete</a></td>
			</tr>
			<%	}
			}
			else{ %>
			<tr>
				<td><%=p.getUsername()%></td>
				<td><%=p.getPassword()%></td>
				<td><%=p.getUsertype()%></td>
				<td><%=p.getNome()%></td>
				<td><%=p.getCognome()%></td>
				<td><%=p.getEmail()%></td>
				<td><%=p.getCitta()%></td>
				<td><%=p.getNazione()%></td>
				<td><a href="/user/preupdate?id=<%=p.getId()%>">Edit</a></td>
				<td><a href="/user/delete?id=<%=p.getId()%>">Delete</a></td>
			</tr>
			<%}%>
		</table>



		<form id="floatright" action="/user/insert" method="post">
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