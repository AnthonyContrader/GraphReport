
function setActive(index){
	
	var nav=document.getElementById('navbar');
	
	for(var i=0;i<nav.children.length-1;i++){
		if(i!=index)
			nav.children[i].classList.remove("active");
		else
			nav.children[i].classList.add("active");
	}
	
}
