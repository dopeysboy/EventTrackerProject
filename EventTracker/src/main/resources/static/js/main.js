window.addEventListener('load', function(e) {
	console.log('Loaded');
	init();
})

function init(){
	loadGameList();
	
	let createSubmit = document.getElementById('createSubmit');
	createSubmit.addEventListener('click', createGame);
}

function xhrRequest(requestMethod, url, readyStateFunction, requestBody){
	let xhr = new XMLHttpRequest();
	
	xhr.open(requestMethod, url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState === xhr.DONE){
			if(xhr.status === 200 || xhr.status === 201 || xhr.status === 202){
				let data = JSON.parse(xhr.responseText);
				readyStateFunction(data);
			} else {
				console.error(`There was an error: ${xhr.status}`);
			}
		}
	}
	
	if(requestBody != undefined){
		xhr.setRequestHeader('Content-type', 'application/json');
		xhr.send(requestBody);
	} else {
		xhr.send();
	}
}