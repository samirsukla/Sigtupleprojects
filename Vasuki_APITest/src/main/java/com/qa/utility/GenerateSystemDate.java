package com.qa.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
//This class will return the Current System Date in DDMMYYYY format
public class GenerateSystemDate {
	
	public String getPresentDate() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		Date date = new Date();
		String currDate = sdf.format(date);
		
		return currDate;

	}

}