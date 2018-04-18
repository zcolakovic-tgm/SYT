console.log('starting sync service!');

let DiffSyncClient = require('diffsync').Client;

let socket = require('socket.io-client');

let client = new DiffSyncClient(socket('http://localhost:3000'), 'form-data');

let data;
client.on('connected', function(){
    // Die Datenreferenz, welche zum synchronisieren verwendet wird.
	// Data-Obj wird sync
    data = client.getData();
    console.log('Verbunden!');
    console.log('Daten akutell:');
    console.log(data);
	
	//Durch Buttonklick wird abgespeichert
	document.getElementById("button").addEventListener('click', () => addElements());
	//Subitem zum abspeichern der Daten
	data['form'] = [];
});

function addElements(){
	alert("add eem")
	//data -> Array, Erzeuge ein Obj, über document.getElementById bekomme ich das HTML-Element, durch value bekommt zeljko den string :)
	data['form'].push({"Produkt" : document.getElementById("form-produkt").value, "Menge" : document.getElementById("form-menge").value, "Note" : document.getElementById("form-note").value})
	client.sync();
}
client.on('synced', function(){
    // Wird aufgerufen, wenn neue Daten vom Server reinkommen
    console.log('Neue Daten vom Server!');
    console.log('Synchronisierte Daten akutell:');
    console.log(data);
	output();
});

function output(){
	var str = "";
	// Durch data durchittarieren, und in html setzen
	for (var i in data['form']){
		str += "<b>Produkt:</b>" + data['form'][i]["Produkt"]+ " <b>Menge:</b>" + data['form'][i]["Menge"] + " <b>Note:</b>" + data['form'][i]["Note"] + "<br>";
	}
	
	document.getElementById("output").innerHTML = str;
}

client.initialize();


