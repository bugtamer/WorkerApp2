package com.worker.persistence.dao;

import java.util.Properties;

import com.worker.util.PropertyValues;

public abstract class DAO {


	// ATTRIBUTES

	public static final int NO_ID = -1;
	protected static String URL;



	// INSTANTIATION

	protected DAO() throws Exception {
		Properties props = new PropertyValues().getPropValues();

		URL = String.format(
				"%s/%s?user=%s&password=%s",
				props.getProperty("url"),
				props.getProperty("database"),
				props.getProperty("user"),
				props.getProperty("password"));

		Class.forName( props.getProperty("dbdriver") ).newInstance();
	}

}