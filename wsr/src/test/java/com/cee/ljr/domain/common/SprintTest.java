package com.cee.ljr.domain.common;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class SprintTest {
	
	private static final String pacomSprint12Name = "PACOM Sprint 12";
	
	@Test
	public void testGetNumber() {
		Sprint sprint12 = new Sprint(pacomSprint12Name, new Date(), new Date());
		Assert.assertTrue(12 == sprint12.getNumber());		
	}
	
	@Test
	public void testSplit() {
		String str = "something";
		
		String[] array = str.split(";");
		
		Assert.assertTrue(str.equals(array[0]));
	}

}
