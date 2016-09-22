package com.cee.ljr.intg.fileparser.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.ljr.intg.fileparser.CsvFileParser;
import com.cee.ljr.intg.jira.domain.JiraIssue;
import com.cee.ljr.utils.FileUtil;

@Component
public class JiraIssuesCsvFileParser {
	private static final Logger log = LoggerFactory.getLogger(JiraIssuesCsvFileParser.class);
	
	private static final int HEADER_ROW = 0;
	
	@Autowired
	CsvFileParser<CSVRecord> csvFileParser;	
	
	private Map<Integer, String> indexToAttributeNameMap;
	
	
	
	
	public List<JiraIssue> parseTasksByDeveloperAndSprints(String csvPaths, String developerName, List<String> sprintNames) {
		List<JiraIssue> jiraIssues = new ArrayList<JiraIssue>();
		
		List<JiraIssue> allIssues = parseAll(csvPaths);
		
		for (JiraIssue jiraIssue : allIssues) {			
			if (!jiraIssue.getDevelopers().contains(developerName)) {
				continue;
			}
			
			for (String sprint : jiraIssue.getSprints()) {
				if (sprintNames.contains(sprint)) {
					jiraIssues.add(jiraIssue);
				}
			}
		}
		
		return jiraIssues;
	}
	
	
	/**
	 * Parses jira issues file(s) associated with the csvPaths.
	 * Takes a single path or multiple ';' delimited paths.
	 * @param csvPaths The path(s) of the files to parse.
	 * @return A list of jira issues.
	 */
	public List<JiraIssue> parseAll(String csvPaths) {
		List<JiraIssue> jiraIssues = new ArrayList<JiraIssue>();
		
		for (String csvPath : csvPaths.split(";")) {
			String path = FileUtil.getAbsolutePath(csvPath);
			List<JiraIssue> issuesToAdd = parse(path);
			jiraIssues.addAll(issuesToAdd);
		}
		
		return jiraIssues;
	}
	
	
	private List<JiraIssue> parse(String csvPath) {
		List<JiraIssue> jiraIssues = new ArrayList<JiraIssue>();
		
		Iterable<CSVRecord> records = csvFileParser.parse(csvPath, false);
		
		int index = 0;
		for(CSVRecord record : records){
			if (index == HEADER_ROW) {
				// process the header row. have to do this because some column headers repeat.
				indexToAttributeNameMap = createIndexMap(record);
			}
			else {
				JiraIssue jiraIssue = mapToJiraIssue(record);
				jiraIssues.add(jiraIssue);
			}
			++index;
		}
		
		return jiraIssues;
	}	
	
	
	private JiraIssue mapToJiraIssue(CSVRecord record) {
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
