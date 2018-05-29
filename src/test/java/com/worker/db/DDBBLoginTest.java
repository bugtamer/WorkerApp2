package com.worker.db;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;


import org.junit.Test;


import com.worker.models.Usuario;

public class DDBBLoginTest {
	

	
	@Test 
	public void testUserNull(){
		
		
		boolean ok = null== DDBB.getInstance().getUsuarios("manolo@workerapp.com", "123456");//email existe y usuario existe
		 
		assertFalse(ok);
	 }
	
	@Test 
	public void testEmailNull(){
		
		Usuario sut = DDBB.getInstance().getUsuarios("manolo@w", "123456");//email no existe y password existe
		 
		assertNull(sut);
	 }
	
	
	@Test 
	public void testPassNull(){
		
		Usuario sut = DDBB.getInstance().getUsuarios("manolo@workerapp.com", "1234");//email existe y password no existe
		 
		assertNull(sut);
	 }
	
	@Test 
	public void testEmailPassNull(){
		
		Usuario sut = DDBB.getInstance().getUsuarios("manolo@workp.com", "1234");//email no existe y password no existe
		 
		assertNull(sut);
	 }

	

}
