function conferma(id,conf){
	var conf = conf || false;
	if(document.getElementById(id).value==""){
		alert("\nAttenzione\n\nInserimento testo necessario");
		return false;
	}
	if(conf){
		if(!confirm("\nStai per inserire: "+ document.getElementById(id).value + "\n\nVuoi conferma l'inserimento?")){
			return false;
		}
	}else
		return true;	
}
