package com.cim.adserver.common;

import java.util.Date;
import java.util.Calendar;

public class AdServerUtil {
	
	public static Date addDuration(Date startDt, Integer duration) {
		if (startDt == null || duration == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDt);
		cal.add(Calendar.SECOND, duration);
		return cal.getTime();
	}
}
