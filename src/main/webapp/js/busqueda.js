function loadBusqueda(){
	let busqueda;
	$.ajax({
		url : './buscar',
		data: $('.search_button').serialize(),
		type: 'POST',
		accepts: 'application/json'
	}).done(function(data)){
		console.log('data:',data);
		let 
		
	}
}