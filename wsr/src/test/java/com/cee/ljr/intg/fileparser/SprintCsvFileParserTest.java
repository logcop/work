package com.cee.ljr.intg.fileparser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.config.ApplicationConfig;
import com.cee.ljr.intg.fileparser.impl.SprintCsvFileParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class SprintCsvFileParserTest {
	
	@Autowired
	SprintCsvFileParser sprintCsvFileParser;
	
	@Test
	public void testParseAll() {
		Iterable<CSVRecord> sprintRecords = sprintCsvFileParser.parseAll();
		
		Assert.assertNotNull(sprintRecords);
		Assert.assertTrue(sprintRecords.iterator().hasNext());
	}
	
	@Test
	public void testParseByNumber() {
		Iterable<CSVRecord> sprintRecords = sprintCsvFileParser.parseByNumber(1);
		
		Assert.assertNotNull(sprintRecords);
		Assert.assertTrue(sprintRecords.iterator().hasNext());
	}
}
