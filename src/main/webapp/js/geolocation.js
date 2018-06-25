function getCurrentGeolocation( errorCallback ) {
	console.log( `Geolocatio 1` );
	let estaGeolocalizado = document.getElementById('latitud');
	if (estaGeolocalizado) {
		console.log( `Geolocatio 2`, estaGeolocalizado );
		if (! estaGeolocalizado.value) { // not defined
			console.log( `Geolocatio 3` );
		    let onSuccessSetCoordinates = (function ( geoCoords ) {
				console.log( `Geolocatio 6` );
		    	document.getElementById('latitud').value = geoCoords.coords.latitude; // decimal degrees
		        document.getElementById('longitud').value = geoCoords.coords.longitude; // decimal degrees
		        //document.getElementById('precision').value = geoCoords.coords.accuracy; // meters
		        //document.getElementById('timestamp').value = + new Date(); // milliseconds
		        console.log(`lat=${document.getElementById('latitud').value}`);
		        console.log(`lon=${document.getElementById('longitud').value}`);
		    });
		    
		    
		    let options = {
		        enableHighAccuracy: true,
		        timeout:            5000,
		        maximumAge:            0
		    };
		    
			
		    if (navigator.geolocation) {
				console.log( `Geolocatio 4` );
		        navigator.geolocation.getCurrentPosition(onSuccessSetCoordinates, errorCallback, options);
		    } else {
				console.log( `Geolocatio 5` );
		        let errorMsg = {code: 0, message: "Your browser do not support geolocation"};
		        console.log( errorMsg );
		        console.log(`lat=${document.getElementById('latitud').value}`);
		        console.log(`lon=${document.getElementById('longitud').value}`);
		        console.log( errorMsg );
		    }
		}
	}
    
}

getCurrentGeolocation( console.log );
console.log( `Geolocatio 0` );