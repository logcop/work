package com.cee.wsr.spreadsheet.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cee.wsr.domain.JiraIssue;
import com.cee.wsr.domain.JiraIssues;
@Component
public class JiraIssuesCsvParser {
	Logger log = LoggerFactory.getLogger(JiraIssuesCsvParser.class);
	private static final int HEADER_ROW = 0;
	private static final int START_ROW = 1;
	private Map<Integer, String> indexToAttributeNameMap;
	
	public JiraIssues parseCsv(String[] csvPaths) {
		JiraIssues jiraIssues = new JiraIssues();
		
		for (String csvPath : csvPaths) {
			addToJiraIssues(csvPath, jiraIssues);
		}
		
		return jiraIssues;
	}	
	
	
	public JiraIssues parseCsv(String csvPath) {
		JiraIssues jiraIssues = new JiraIssues();
		
		addToJiraIssues(csvPath, jiraIssues);
		
		return jiraIssues;
	}
	
	
	public void addToJiraIssues(String csvPath, JiraIssues jiraIssues) {
		log.debug("attempting to create jira issues from: " + csvPath);
		//Create the CSVFormat object
		//CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
		Iterable<CSVRecord> records = null;
		try {
			Reader in = new FileReader(csvPath);
			records = CSVFormat.EXCEL.parse(in);
		}
		catch (FileNotFoundException fnfe) {
			log.error("Unable to find: " + csvPath, fnfe);
			return;
		}
		catch (IOException ioe) {
			log.error("Unable to process csv file: " + csvPath);
			return;
		}
		
		
		//initialize the CSVParser object
		//CSVParser parser = new CSVParser(new FileReader(csvPath), format);
		
		int index = 0;
		for(CSVRecord record : records){
			if (index == HEADER_ROW) {
				// process the header row. have to do this because some column
				// headers repeat.
				indexToAttributeNameMap = createIndexMap(record);
			}
			else {
				jiraIssues.addIssue(process(record));
			}
			++index;
		}
	}
	
	private JiraIssue process(CSVRecord record) {
		JiraIssue jiraIssue = new JiraIssue();
		
		for (int i = 0; i < record.size(); i++) {
			String attributeName = indexToAttributeNameMap.get(i);
			String value = record.get(i);
			jiraIssue.addAttribute(attributeName, value);
		}
		
		return jiraIssue;
	}
	
	private Map<Integer, String> createIndexMap(CSVRecord record) {
		Map<Integer, String> indexToAttributeMap = null;
		
		int numOfCellsInRow = record.size();
		
		indexToAttributeMap = new HashMap<Integer, String>(numOfCellsInRow, 1.0f);
		
		int index = 0;
		for (String attributeName : record) {
			indexToAttributeMap.put(index, attributeName);
			++index;
		}
		
		return indexToAttributeMap;
	}
}
