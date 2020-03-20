<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List" import="java.util.ArrayList"
	import="it.contrader.dto.DataSetDTO" import="it.contrader.dto.UserDTO"%> <%--	IMPORTARE QUANTO NECESSARIO + TIPI ENUM --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GraphHome</title>
<link href="../css/toniostyle.css" rel="stylesheet"> <%-- PONDERARE SCELTA DEI CSS --%>
<script type="text/javascript" src="../js/toniojs.js"></script>
</head>
<body>
	<div class="cols half">
<%
		List<DataSetDTO> list = new ArrayList<DataSetDTO>(); //(List <DataSetDTO>) request.getAttribute("list");					// INIZIALIZZARE QUANTO NECESSARIO
																									//  Vari enum
																	
		if(session.getAttribute("usertype").toString().equalsIgnoreCase("admin")){					//SE ADMIN STAMPA LISTA UTENTI
			List<UserDTO> listU = (List <UserDTO>) request.getAttribute("listUtente");
			%>
			<form class="center" id="visDataSetUt" action="viewUt" style="width:max-content" method="post">
			<label>Visualizza i grafici dell'utente</label>
			<select name="idUtVis" onchange="document.getElementById('visDataSetUt').submit();" style="width:max-content">
				<option value="<%=session.getAttribute("userid")%>">Tuoi Personali</option>
				<%
				for(UserDTO u : listU){
					if(u.getId()!=Long.parseLong(session.getAttribute("userid").toString())){
					%>
					
					<option value="<%=u.getId()%>" <% if(request.getAttribute("idUtVis")!=null){if(Long.parseLong(request.getAttribute("idUtVis").toString())==u.getId()){ %>selected<% } } %>><%=u.getUsername()%></option>
					
					<%
					}
				}
				%>
				</select>
			</form>
			<%
		}																							// FINE IF
		
		if(!list.isEmpty()){																		//SE CI SONO GRAFICI CREATI ELENCALI
			for (DataSetDTO ds : list){
				
				}
		}else{																						//ALTRIMENTI DI CHE NON CE NE SONO
		%>
		<h1 style="margin-top:80px;">Nessun grafico presente!</h1>
		<%} %>																						
		</div></div>														
	</div>																							<!--  FINE IF E FINE COLONNA SINISTRA -->		
	<div class="cols half">																			<!--  INIZIO ZONA DESTRA PER CREAZIONE NUOVO GRAFICO -->
	<h1>Crea nuovo grafico</h1>
	<p class="center">Successivamente alla creazione sarà possibile rivisitare i vari aspetti caratterizzanti.</p>
	<form class="center" action="creategraph" style="min-width:270px" onsubmit="return verificaCrea()" method="post">
		<label><strong>Titolo</strong></label>
		<input class="noFloat" name="" value="" placeholder="Inserisci titolo"/>
		<label><strong>Set di valori</strong></label>
		<div class="contenitor">
			<select id="cump" name="ump">
				<option value="" style="color:grey">DataSet</option>
				<%
//					for(UnitaMisuraDTO um : listUM){
					%>
					
<%-- 					<option value="<%=um.getId()%>]<%=um.getNome() %>"><%=um.getNome()%></option>  --%>
					
					<%
// 					}
				%>
			</select>
			<select id="cums" name="ums">
				<option value="" style="color:grey">Set di valori</option>
				<%
// 				for(UnitaMisuraDTO um : listUM){
					%>
					
<%-- 					<option value="<%=um.getId()%>]<%=um.getNome()%>"><%=um.getNome()%></option> --%>
					
					<%
// 					}
				%>
			</select>
		</div>
		<div class="contenitor">
			<select id="cump" name="ump">
				<option value="" style="color:grey">DataSet</option>
				<%
//					for(UnitaMisuraDTO um : listUM){
					%>
					
<%-- 					<option value="<%=um.getId()%>]<%=um.getNome() %>"><%=um.getNome()%></option>  --%>
					
					<%
// 					}
				%>
			</select>
			<select id="cums" name="ums">
				<option value="" style="color:grey">Set di valori</option>
				<%
// 				for(UnitaMisuraDTO um : listUM){
					%>
					
<%-- 					<option value="<%=um.getId()%>]<%=um.getNome()%>"><%=um.getNome()%></option> --%>
					
					<%
// 					}
				%>
			</select>
		</div>
		<button type="submit">Crea</button>
	</form>
	</div>
	<%
if(request.getAttribute("err")!=null){
	%>
	<div id="err" class="msgerr">
	<label class="closeerr" onclick="chiudiParente(this)">[X] Chiudi</label>
	<%
	switch (Integer.parseInt(request.getAttribute("err").toString())){
		case 0:
			%>
				<strong>Attenzione:</strong> Funzionalità in fase di sviluppo!<br>Si consiglia di riprovare in seguito. 
			<%
			break;
		case 1:
			%>
				<strong>Impossibile creare nuovo:</strong> DataSet esistente!<br>Si consiglia di modificare quello esistente. <!-- GESTIONE DEGLI ERRORI DA DB -->
			<%
			break;
	}
	%>
	</div>
	<%
}
%>
</body>

<script>
	window.addEventListener('resize',resize);
	resize();
</script>

</html>
