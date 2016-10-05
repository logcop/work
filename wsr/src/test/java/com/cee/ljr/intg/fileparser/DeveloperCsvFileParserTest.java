package com.cee.ljr.intg.fileparser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.config.ApplicationConfig;
import com.cee.ljr.intg.fileparser.impl.DeveloperCsvFileParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class DeveloperCsvFileParserTest {
	
	@Autowired
	DeveloperCsvFileParser developerCsvFileParser;
	
	@Test
	public void testParseAll() {
		Iterable<CSVRecord> developers = developerCsvFileParser.parseAll();
		
		Assert.assertNotNull(developers);
		Assert.assertTrue(developers.iterator().hasNext());
		//System.out.println(developers);		
	}
	

	@Test
	public void testParseForName() {
		String nameInJira = "Chuck";
		
		CSVRecord developer = developerCsvFileParser.parseForName(nameInJira);
		
		Assert.assertNotNull(developer);
	}
}
