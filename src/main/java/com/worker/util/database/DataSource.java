package com.worker.util.database;

/**
 * 
 * @author Bugtamer
 *
 */
public enum DataSource {
	
	usuarios      ("http://www.mocky.io/v2/5b0af08c2f00009200ec4c6b"),
	manitas       ("http://www.mocky.io/v2/5b0ad2d92f00007600ec4c55"),
	manitas2      ("http://www.mocky.io/v2/5b0ae39d2f00007800ec4c67"),
	chatMessages  ("http://www.mocky.io/v2/5ae039093200005e00510a5d"),
	ticket        ("http://www.mocky.io/v2/5ae0f2f03200006d00510da2"),
	testOK        ("http://www.mocky.io/v2/5b096ec43500005d001262a2"),
	// JSON prescindibles
	loginTrue     ("http://www.mocky.io/v2/5ae1741c2d000057009d7c06"),
	loginFalse    ("http://www.mocky.io/v2/5ae1e1442d000029009d7f2d"),
	searchResults ("http://www.mocky.io/v2/5b0a97392f00005b00ec4bf6");
	
	
	// SERVICIOS
	
	public String getURL() {
		return url;
	}
	
	@Override
	public String toString() {
		return url;
	}
	
	// DETALLES DE IMPLEMENTACIÓN
	
	private final String url;
	
	private DataSource(String url) {
		this.url = url;
	}
}
