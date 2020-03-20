function verificaCrea(){
	if(document.getElementById('cump').value==document.getElementById('cums').value){
		alert('\nAttenzione\n\nLe unita di misura dei dati non possono essere uguali!\n');
		return false;
	}else
		return true;
}

function confDelDS(){
	return confirm('\nCancellazione in corso!\n\nStai per eliminare definitivamente questo DataSet.\nVuoi proseguire?');
}

function checkColonna(id,conf){
	var conf = conf || false;
	if(!document.getElementById('btnSalva').disabled){
		if(!confirm('\nAttenzione!\n\nProseguendo perderai le ultime modifiche non ancora salvate.\nVuoi procedere?'))
			return false;
	}
	if(conf)
		 return confirm('\nCancellazione in corso!\n\nStai per eliminare definitivamente la colona selezionata.\nVuoi procedere?');
	else
		return true;
}

function createDivInput(i){
	var input=document.createElement("INPUT");
	input.setAttribute("type", "text");
	input.setAttribute("class", "tableInput");
	input.setAttribute("value", " ");
	input.setAttribute("onchange",'activeSave()')
	
	var div=document.createElement('DIV');
	div.setAttribute("class", "tr");
	div.setAttribute("name", "row"+i);
	
	div.appendChild(input);
	return div;
}

function createDivX(i){
	var img=document.createElement("IMG");
	img.setAttribute("src", "../img/remove.png");
	img.setAttribute("title", "Elimina riga");
	img.setAttribute("onclick", "delRow("+i+")");
	
	var div=document.createElement('DIV');
	div.setAttribute("class", "tr");
	div.setAttribute("name", "row"+i);
	div.style.width="40px";
	
	div.appendChild(img);
	return div;
}

function addRow(){
	var col=document.getElementsByName('um');
	for(var i=0;i<col.length;i++){
		if(i!=(col.length-1)){
			col[i].insertBefore(new createDivInput(col[i].childElementCount-1),col[i].children[col[i].childElementCount-1]);
		}else{
			col[i].insertBefore(new createDivX(col[i].childElementCount-1),col[i].children[col[i].childElementCount-1]);
		}
	}
	repaint()
}

function delRow(index){
	var row=document.getElementsByName("row"+index);
	var n=row.length;
	for(var i=0;i<n;i++){
		row[0].remove();
	}
	repaint()
	activeSave()
}

function activeSave(){
	document.getElementById('btnSalva').removeAttribute("disabled");
}

function submitUpdate(){
	
	var col=document.getElementsByName('um');
	var str="",tmp="",n=col.length-1;
	
	for(var i=0;i<n;i++){
		
		for(var j=0;j<col[i].childElementCount-1;j++){
			if(j==0){
				str+=col[i].children[j].querySelector("input[name='idDataSet']").value+"]";
				str+=col[i].children[j].querySelector("input[name='comment']").value+"]";
			}else{
				tmp=col[i].children[j].querySelector("input[type='text']").value.replace(/ /g,"");
				if(tmp=="" || tmp==null)
					str+=" _";
				else
					str+=tmp.replace(".",",")+"_";
			}
		}
		str+="}";
	}
	document.getElementById('updValore').value=str;
	document.getElementById('formDSUpdate').submit();
}

function repaint(){
	var col=document.getElementsByName('um');
	
	var col=document.getElementsByName('um');
	var n=col.length;
	
	for(var i=0;i<n;i++){
		for(var j=1;j<col[i].childElementCount;j++){
			if((j-1)%2==0){
				col[i].children[j].classList.add('grey');
			}else{
				col[i].children[j].classList.remove('grey');
			}
		}
	}
}

function chiudiParente(dom){
		dom.parentElement.remove();
}

function resize(){
	var dom=document.querySelectorAll('div[class="cols half"]>div>div[class="center"]');
	var domHalf=document.querySelectorAll('div[class="cols half"]');
	
	var max=0;
	for(var j=0;j < dom.length;j++){
		if(dom[j].offsetWidth > max) max=dom[j].offsetWidth;
	}

	if(max>(document.body.offsetWidth/2)+1){
		
		for(var i=0;i<domHalf.length;i++){
			domHalf[i].style.width="100%";
		}
		try{
			document.getElementById('btnSalva').parentElement.style.right="0px";
		}catch(e){}
		
	}else{
		
		if((document.body.offsetWidth+1)>(max/2)+1){
			
			for(var i=0;i<domHalf.length;i++){
				domHalf[i].style.width="50%";
			}
			try{
				document.getElementById('btnSalva').parentElement.style.right=((document.body.offsetWidth/2)-73)+"px";
			}catch(e){}
			
		}
	}
}

function editComm(x,n){
	var txt = prompt("Digita il commento:", x),
		vecchio=document.getElementById('comm'+n).value;
	if(txt!=null && vecchio!=txt){
		document.getElementById('comm'+n).value=txt;
		if(txt!="")
			document.getElementById('tool'+n).innerHTML='<p>'+txt+'</p><p class="sugg">Clicca per modificare</p>';
		else
			document.getElementById('tool'+n).innerHTML='<p class="sugg">Clicca per inserire un commento</p>';
		activeSave();
	}
}