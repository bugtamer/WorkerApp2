package com.worker.db;

/**
 * 
 * @author Bugtamer
 *
 */
public enum DataSource {
	
	chatMessages  ("http://www.mocky.io/v2/5ae039093200005e00510a5d"),
	loginTrue     ("http://www.mocky.io/v2/5ae1741c2d000057009d7c06"),
	loginFalse    ("http://www.mocky.io/v2/5ae1e1442d000029009d7f2d"),
	searchResults ("http://www.mocky.io/v2/5b0942543500007900126279"),
	testOK        ("http://www.mocky.io/v2/5b096ec43500005d001262a2"),
	ticket        ("http://www.mocky.io/v2/5ae0f2f03200006d00510da2");
	
	
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
