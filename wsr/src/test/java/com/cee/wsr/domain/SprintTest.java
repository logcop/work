package com.cee.wsr.domain;

import junit.framework.TestCase;

import org.junit.Assert;

import com.cee.wsr.domain.common.Sprint;

public class SprintTest extends TestCase {
	
	private static final String pacomSprint12Name = "PACOM Sprint 12";
	
	public void testGetNumber() throws Exception {
		Sprint sprint12 = new Sprint(pacomSprint12Name);
		Assert.assertTrue(12 == sprint12.getNumber());		
	}

}
