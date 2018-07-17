package com.worker.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timestamp {
	
	private Timestamp() { }
	
	
	public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat( TIME_FORMAT );
		return sdf.format( new Date() );
	}
	
	
	public static String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat( TIME_FORMAT );
		return sdf.format( date );
	}
	
	
	/**
	 * recorta la respuesta del servidor cuando se recupera como String en vez de Date
	 * @param date >>> 2018-07-17 22:58:17.0
	 * @return 2018-07-17 22:58:17
	 */
	public static String trimDateFromDB(String date) {
		return date.substring(0, date.length() - 2);
	}

}
