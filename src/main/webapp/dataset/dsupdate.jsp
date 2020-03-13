<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.DataSetDTO" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
STI CAZZI A PRIORI
<%
List<DataSetDTO> list = (List <DataSetDTO>) request.getAttribute("dataset");
for(DataSetDTO ds : list){ %><%=ds.getUnitaMisura() %> <% }
%>

</body>
</html>