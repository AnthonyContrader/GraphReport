function conferma(id,conf){
	var conf = conf || false;
	if(document.getElementById(id).value==""){
		alert("\nAttenzione\n\nInserimento testo necessario");
		return false;
	}
	if(conf){
		return confirm("\nInserimento in corso!\n\nVuoi confermare l'inserimento?")
	}else
		return true;
}