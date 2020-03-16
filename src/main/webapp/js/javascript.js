function setActive(index){
	
	var nav=document.getElementById('navbar');
	
	for(var i=0;i<nav.childElementCount-1;i++){
		if(i!=index)
			nav.children[i].className="";
		else
			nav.children[i].className="active";
	}
	
}
