package com.cee.ljr.intg.fileparser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cee.ljr.config.ApplicationConfig;
import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.intg.fileparser.impl.SprintCsvFileParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class SprintCsvFileParserTest {
	
	@Autowired
	SprintCsvFileParser sprintCsvFileParser;
	
	@Test
	public void testParseAll() {
		List<Sprint> sprints = sprintCsvFileParser.parseAll();
		
		Assert.assertNotNull(sprints);
		Assert.assertFalse(sprints.isEmpty());
		System.out.println(sprints);		
	}
	
	@Test
	public void testParseByNumber() {
		List<Sprint> sprints = sprintCsvFileParser.parseByNumber(1);
		
		Assert.assertNotNull(sprints);
		Assert.assertFalse(sprints.isEmpty());
		System.out.println(sprints);		
	}
}
