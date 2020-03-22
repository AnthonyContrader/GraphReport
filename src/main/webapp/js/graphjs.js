function checkCreate(){
	if(document.getElementById('assex').value==document.getElementById('assey').value){
		alert("Impossibile creare!\n\nSpecificare set di valori differenti sui due assi.");
		return false;
	}else
		return true;
}

