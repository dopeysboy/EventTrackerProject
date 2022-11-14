function loadGameList(e){
	if(e != undefined){
		e.preventDefault();
	}
	
	let requestMethod = 'GET';
	let url = '/api/games';
	let readyStateFunction = displayGameList;
	
	xhrRequest(requestMethod, url, readyStateFunction);
}

function getSingleGame(gameId){
	let requestMethod = 'GET';
	let url = `/api/games/id/${gameId}`;
	let readyStateFunction = displaySingleGame;
	
	xhrRequest(requestMethod, url, readyStateFunction);
}

function deleteGameRequest(gameId){
	let requestMethod ='DELETE';
	let url = `/api/games/id/${gameId}`;
	let readyStateFunction = loadGameList;
	
	xhrRequest(requestMethod, url, readyStateFunction);
}

function editGameRequest(game, gameId){
	let requestMethod = 'PUT';
	let url = `/api/games/id/${gameId}`;
	let readyStateFunction = function(data){
		console.log(data);
	};
	let requestBody = JSON.stringify(game);
	
	xhrRequest(requestMethod, url, readyStateFunction, false, requestBody);
	loadGameList();
}

function createGameRequest(game){
	let requestMethod = 'POST';
	let url = "/api/games";
	let readyStateFunction = function(data){
		console.log(data);
	};
	let requestBody = JSON.stringify(game);
	
	xhrRequest(requestMethod, url, readyStateFunction, false, requestBody);
	loadGameList();	
}

var createGame = function(e){
	e.preventDefault();
	console.log('In createGame!');
	
	let formTitle = document.getElementById('createTitle').value;
	let formDescription = document.getElementById('createDescription').value;
	let formMsrp = document.getElementById('createMsrp').value;

	let game = {
		title: formTitle,
		description: formDescription,
		msrp: formMsrp
	};
	
	createGameRequest(game);
	gameCreation.reset();
}

var deleteGame = function(e){
	e.preventDefault();
	
	let confirmed = confirm('Are you sure you want to delete this game?');
	
	if(confirmed){
		let gameId = document.getElementById('id');
		deleteGameRequest(gameId.textContent);
	}
}

var editGame = function(e){
	e.preventDefault();
	
	let formDiv = document.getElementById('gameEditContainer');
	formDiv.style.display = '';
	
	let gameListDiv = document.getElementById('gameListDiv');
	gameListDiv.style.display = 'none';
	
	document.gameCreation.style.display = 'none';
	
	let id = document.getElementById('id').textContent;
	let title = document.getElementById('title').textContent;
	let description = document.getElementById('description').textContent;
	let msrp = document.getElementById('msrp').textContent.substring(1);
//	let publishers = document.getElementById('publishers');
//	let genres = document.getElementById('genres');
	
	let formTitle = document.getElementById('editTitle');
	let formDescription = document.getElementById('editDescription');
	let formMsrp = document.getElementById('editMsrp');
	
	formTitle.value = title;
	formDescription.value = description;
	formMsrp.value = msrp;
	
	let submit = document.getElementById('editSubmit');
	submit.addEventListener('click', function(e){
		e.preventDefault();
		let game = {
			title: formTitle.value,
			description: formDescription.value,
			msrp: formMsrp.value
		};
		
		editGameRequest(game, id);
		formDiv.style.display = 'none';
		document.gameCreation.style.display = '';
		gameListDiv.style.display = '';
	});
	
	let cancel = document.getElementById('editCancel');
	cancel.addEventListener('click', function(e){
		e.preventDefault();
		
		loadGameList();
		formDiv.style.display = 'none';
		document.gameCreation.style.display = '';
		gameListDiv.style.display = '';
	});
}

var displayGameList = function(games){
	let displayProperties = ['id', 'title', 'msrp', 'description'];
	let table = document.getElementById('gameTable');
	let tbody = document.getElementById('tableBody');
	
	let singleGame = document.getElementById('singleGame');
	
	//if a single game has been displayed, remove it to clean page
	if(singleGame != undefined){
		singleGame.parentElement.removeChild(singleGame);
	}
	
	//clean table so duplicate data doesn't get added, and unhide table
	table.style.display = '';
	tbody.textContent = '';

	for(let game of games){
		let tr = document.createElement('tr');
		
		for(let i in displayProperties){
			let td = document.createElement('td');
			td.textContent = game[displayProperties[i]];
			td.addEventListener('click', function(e){
				getSingleGame(game['id']);
			});
			
			tr.appendChild(td);
		}
		
		tbody.appendChild(tr);
	}
}

var displaySingleGame = function(game){
	let gameDiv = document.getElementById('gameListDiv');
	let table = gameDiv.firstElementChild;
	
	//elements to add to the div thats created
	let elements = [];
	
	//hide table, so headers don't show up
	table.style.display = 'none';

	let singleGame = document.createElement('div');
	singleGame.id = 'singleGame';
	
	let h1 = document.createElement('h1');
	h1.textContent = game['title']
	h1.id = 'title';
	elements.push(h1);
	
	elements.push(document.createElement('hr'));
	
	let id = document.createElement('p');
	id.textContent = game.id;
	id.id = 'id';
	id.style.display = 'none';
	elements.push(id);
	
	let p = document.createElement('p');
	p.textContent = `ID: ${game['id']}`;
	elements.push(p);
	
	let msrp = document.createElement('p');
	msrp.textContent = `$${game['msrp']}`;
	msrp.id = 'msrp';
	elements.push(msrp);
		
	let bq = document.createElement('blockquote');
	bq.textContent = game['description'];
	bq.id = 'description';
	elements.push(bq);
	
	let publisherTitle = document.createElement('h3');
	publisherTitle.textContent = 'Publishers:';
	elements.push(publisherTitle);
	
	let publishers = document.createElement('ul');
	publishers.id = 'publishers';
	
	for(let pub of game['publishers']){
		let li = document.createElement('li');
		li.textContent = pub.name;
		publishers.appendChild(li);
	}
	elements.push(publishers);
	
	let genreTitle = document.createElement('h3');
	genreTitle.textContent = 'Genres:';
	elements.push(genreTitle);
	
	let genres = document.createElement('ul');
	genres.id = 'genres';
	
	for(let gen of game['genres']){
		let li = document.createElement('li');
		li.textContent = gen.name;
		genres.appendChild(li);
	}
	elements.push(genres);
	
	let editBtn = document.createElement('button');
	editBtn.textContent = 'Edit this game';
	editBtn.className = 'btn btn-secondary';
	editBtn.name = 'edit';
	editBtn.addEventListener('click', editGame);
	elements.push(editBtn);
	
	let deleteBtn = document.createElement('button');
	deleteBtn.textContent = 'Delete this game';
	deleteBtn.className = 'btn btn-danger';
	deleteBtn.name = 'delete';
	deleteBtn.addEventListener('click', deleteGame);
	elements.push(deleteBtn);
	
	let returnBtn = document.createElement('button');
	returnBtn.textContent = 'Return to all games';
	returnBtn.className = 'btn btn-primary';
	returnBtn.name = 'return';
	returnBtn.addEventListener('click', loadGameList);
	elements.push(returnBtn);
	
	for(let el of elements){
		singleGame.appendChild(el);
	}
	gameDiv.appendChild(singleGame);	
}