<html lang="it">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Welcome page">

<!-- Custom styles for this template -->
<link href="../css/vittoriostyle.css" rel="stylesheet">

<script type="text/javascript" src="../js/javascript.js"></script>
</head>
<body class="main">

<h1>WELCOME <%= request.getAttribute("username").toString().toUpperCase() %></h1>

<div class="img1">
<img class="image" src="../img/GraphReportHome.jpg">
<img class="frase1" src="../img/Frase1.png">
<img class="frase2" src="../img/Frase2.png">
<img class="frase3" src="../img/Frase3.png">
<img class="frase4" src="../img/Frase4.png">
</div>

</body>
</html>