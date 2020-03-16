<%@ page import="it.contrader.dto.UserDTO" import="java.util.*"%>
<html lang="it">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Welcome page">

<!-- Custom styles for this template -->
<link href="/css/vittoriostyle.css" rel="stylesheet">

<script type="text/javascript" src="js/javascript.js"></script>
</head>
<body class="main">
<% UserDTO user = (UserDTO) session.getAttribute("user"); %>
<h1>Welcome <%= user.getUsername()%></h1>

QUI TANTA ROBA

</body>
</html>