package com.cee.ljr.intg.jira.domain;

import org.junit.Assert;
import org.junit.Test;

public class JiraAttributeTest {

	@Test
	public void testGet() {
		String value = "Comment";
		
		JiraAttribute ja = JiraAttribute.get(value);
		Assert.assertTrue(ja.equals(JiraAttribute.COMMENT));
	}
}
