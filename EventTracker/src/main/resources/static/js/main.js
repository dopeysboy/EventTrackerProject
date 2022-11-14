window.addEventListener('load', function(e) {
	console.log('Loaded');
	init();
})

function init(){
	loadGameList();
	
	let createSubmit = gameCreation.submit;
	createSubmit.addEventListener('click', createGame);
}

function xhrRequest(requestMethod, url, readyStateFunction, isAsync=true, requestBody){
	let xhr = new XMLHttpRequest();
	
	xhr.open(requestMethod, url, isAsync);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState === xhr.DONE){
			if(xhr.status === 200 || xhr.status === 201 || xhr.status === 202){
				if(xhr.responseText != ''){
					console.log('Doing readyStateFunction with data');
					let data = JSON.parse(xhr.responseText);
					readyStateFunction(data);
				} else {
					console.log('Doing readyStateFunction');
					readyStateFunction();
				}
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