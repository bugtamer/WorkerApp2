package com.worker.persistence;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.worker.util.PropertyValues;

public abstract class ContextMocking {
	private static Logger logger = Logger.getLogger("Test");
	private static Properties properties = null;
	protected static SessionFactory factory=null;

	public static void setUpContext(){
		PropertyValues pv = new PropertyValues();

		try {
			properties = pv.getPropValues();

            // Create initial context
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
    		InitialContext ic=null;
    		try{
    			 ic = new InitialContext();
    		}
    		catch (NamingException ex){
    		  	logger.log(Level.SEVERE,ex.getMessage());
    			ex.printStackTrace();
    		}

            ic.createSubcontext("java:");
            ic.createSubcontext("java:/comp");
            ic.createSubcontext("java:/comp/env");
            ic.createSubcontext("java:/comp/env/jdbc");

            // Construct DataSource
            MysqlDataSource  ds = new MysqlDataSource();
            ds.setURL( properties.getProperty("url")+"/"+properties.getProperty("database") );
            ds.setUser( properties.getProperty("user") );
            ds.setPassword( properties.getProperty("password") );

            ic.bind("java:/comp/env/"+properties.getProperty("pooldataSource"), ds);

        } catch (NamingException ex) {
        	logger.log(Level.SEVERE,ex.getMessage());
        	ex.printStackTrace();
        } catch (IOException ex) {
        	logger.log(Level.SEVERE,ex.getMessage());
        	ex.printStackTrace();
		}
	}
}