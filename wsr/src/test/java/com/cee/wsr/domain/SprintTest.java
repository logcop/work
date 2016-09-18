package com.cee.wsr.domain;

import junit.framework.TestCase;

import org.junit.Assert;

import com.cee.ljr.domain.common.ProjectSprint;

public class SprintTest extends TestCase {
	
	private static final String pacomSprint12Name = "PACOM ProjectSprint 12";
	
	public void testGetNumber() throws Exception {
		ProjectSprint sprint12 = new ProjectSprint(pacomSprint12Name);
		Assert.assertTrue(12 == sprint12.getNumber());		
	}

}
