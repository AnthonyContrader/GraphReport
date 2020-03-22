<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List" import="it.contrader.dto.DataSetDTO"
	import="it.contrader.dto.GraphDTO" import="it.contrader.dto.UserDTO" import="it.contrader.dto.CategoriaDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GraphHome</title>
<link href="../css/toniostyle.css" rel="stylesheet">
<script type="text/javascript" src="../js/graphjs.js"></script>
</head>
<body>
	<div class="cols half">
<%
		List<GraphDTO> list = (List<GraphDTO>) request.getAttribute("listGraph");
		List<DataSetDTO> listDS = (List<DataSetDTO>) request.getAttribute("listC");
		String categorie="", unitM="", jsArrayListUM = "[";
		Long[] noDuplicate = new Long[listDS.size()];
		int i;
		boolean unique;
		
		//GENERA LISTA CATEGORIE PRIVA DI DUPLICATI E LISTA UNITAMISURA PER IL JS
		for(DataSetDTO ds : listDS){
			jsArrayListUM+="\""+ds.getCategoria()+"§"+ds.getId()+"§"+ds.getUnitaMisuraN()+" "+ds.getCommento()+"\",";
			i=0;
			unique=true;
			while((i<noDuplicate.length)&& unique){
				if(noDuplicate[i]!=ds.getCategoria()){
					if(noDuplicate[i]==null){
						noDuplicate[i]=ds.getCategoria();
						categorie+="<option value=\""+ds.getCategoria()+"\">"+ds.getCategoriaN()+"</option>";
						unique=false;
						}
				}else
					unique=false;
				i++;
			}
			
		}
		jsArrayListUM=jsArrayListUM.substring(0,jsArrayListUM.length() - 1) + "]";
		
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
		}																							
		
		if(!list.isEmpty()){																		
			for (GraphDTO ds : list){
					%>
					<%= ds %>
					<% 
				}
		}else{																						
		%>
		<h1 style="margin-top:80px;">Nessun grafico presente!</h1>
		<%} %>																						
		</div></div>														
	</div>																								
	<div class="cols half">																			
	<h1>Crea nuovo grafico</h1>
	<p class="center">Successivamente alla creazione sarà possibile rivisitare i vari aspetti caratterizzanti.</p>
	<form class="center" action="creategraph" style="min-width:270px" onsubmit="return checkCreate()" method="post">
		<label><strong>Titolo</strong></label>
		<input class="noFloat" name="tit" value="" placeholder="Inserisci titolo" required/>
		<label><strong>Set di valori</strong></label>
		<div class="contenitor">
			<select id="cat" onchange="popolaAssi()" required>
				<option value="" style="color:grey">DataSet</option>
				<%= categorie %>
			</select>
			<select id="assex" name="assex" disabled required>
				<option value="" style="color:grey">Asse X</option>
			</select>
			<select id="assey" name="assey" disabled required>
				<option value="" style="color:grey">Asse Y</option>
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
	function popolaAssi(){
		var list = <%= jsArrayListUM %>, 
			cat=document.getElementById('cat').value
			splitted="",html="";
		if(cat!=""){
			for(var i=0;i<list.length;i++){
				splitted=list[i].split("§");
				if(splitted[0]==cat)
					html+='<option value="'+splitted[1]+'">'+splitted[2]+'</option>';
			}
			document.getElementById("assex").innerHTML='<option value="" style="color:grey">Asse X</option>'+html;
			document.getElementById("assey").innerHTML='<option value="" style="color:grey">Asse Y</option>'+html;
			
			document.getElementById("assex").removeAttribute('disabled');
			document.getElementById("assey").removeAttribute('disabled');
		}else{
			document.getElementById("assex").innerHTML='<option value="" style="color:grey">Asse X</option>';
			document.getElementById("assey").innerHTML='<option value="" style="color:grey">Asse Y</option>';
			
			document.getElementById("assex").setAttribute('disabled','disabled');
			document.getElementById("assey").setAttribute('disabled','disabled');
		}
	}
</script>

</html>
