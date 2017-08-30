package com.meeno.framework.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class JUnitTest {
	
	@Test
	public void dateTest(){
		
		Calendar calendar = new GregorianCalendar();
		int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
		System.out.println("ZONE_OFFSET : " + zoneOffset);
	}
}
