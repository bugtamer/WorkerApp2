(function(){
	let count = 2;
	setInterval(function(){
	    count--;
	    if (count == 0) {
	    	window.location = document.getElementById('urlDestino').value;
	    }
	}, 1000);
})();