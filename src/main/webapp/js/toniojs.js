function verificaCrea(){
	if((document.getElementById('cc').value=="") || (document.getElementById('cump').value=="") || (document.getElementById('cums').value=="")){
		alert('\nAttenzione\n\nSi prega di compilare tutti i campi!\n');
		return false;
	}else{
		if(document.getElementById('cump').value==document.getElementById('cums').value){
			alert('\nAttenzione\n\nLe tipologie di dati non possono essere uguali!\n');
			return false;
		}else
		return true;
	}
}

function confDelDS(){
	return confirm('\nAttenzione!\n\nStai per eliminare definitivamente questo DataSet\n\nVuoi proseguire?');
}