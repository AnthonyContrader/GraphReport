
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Homepage for Admin ">
<meta name="author" content="Vittorio Valent">

<title>GraphReport Home</title>

<!-- Custom styles for this template -->
<link href="../css/vittoriostyle.css" rel="stylesheet">

<script type="text/javascript" src="../js/javascript.js"></script>
</head>
<body style="min-width:760px">
<%@include file="css/header.jsp"%>

<div class="navbar" id="navbar">
  <a class="active" href="/homeStructure.jsp" onclick="setActive(0)">Home</a>
  <a href="/user/getall" target="frameBody" onclick="setActive(1)">Utente</a>
  <a href="/dataset/home" target="frameBody" onclick="setActive(2)">DataSet</a>
  <a href="/categoria/getall" target="frameBody" onclick="setActive(3)">Categoria</a>
  <a href="/unitamisura/getall" target="frameBody" onclick="setActive(4)">Unità di misura</a>
  <a href="/graph/home" target="frameBody" onclick="setActive(5)">Grafico</a>
  <a href="/user/logout" id="logout">Logout</a>
</div>

<iframe class="iframeinvisible" src="/user/welcome" name="frameBody">


</iframe>


<%@ include file="css/footer.jsp" %>

</body>
<script type="text/javascript">
	<% if(request.getAttribute("err")!=null){
	switch (Integer.parseInt(request.getAttribute("err").toString())){
		case 1:
			%>
			alert("Non è possibile eliminare un account con dei grafici");
			<%
		break;
	}
	}
	%>

</script>
</html>