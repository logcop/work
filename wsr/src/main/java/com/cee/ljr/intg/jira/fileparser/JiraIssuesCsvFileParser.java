package com.cee.ljr.intg.jira.fileparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.intg.fileparser.impl.CsvReaderFileParser;
import com.cee.ljr.intg.jira.domain.JiraAttribute;
import com.cee.ljr.intg.jira.domain.JiraIssue;
import com.cee.ljr.intg.mapping.JiraIssueMapper;
import com.cee.ljr.utils.FileUtil;

@Component
@Deprecated
public class JiraIssuesCsvFileParser {
	private static final Logger log = LoggerFactory.getLogger(JiraIssuesCsvFileParser.class);
	
	private static final int HEADER_ROW_INDEX = 0;
	
	@Autowired
	CsvReaderFileParser csvFileParser;	
	
	@Autowired
	JiraIssueMapper jiraIssueMapper;
	
	//private Map<Integer, String> indexToAttributeNameMap;
	//private Map<String, List<Integer>> attributeNameToListOfIndexesMap;	
	
	//@Deprecated
	/*public List<JiraIssue> parseTasksByDeveloperAndSprints(String csvPaths, String developerName, List<String> sprintNames) {
		List<JiraIssue> jiraIssues = new ArrayList<JiraIssue>();
		
		//Date startDate = DateUtil.get
		
		List<JiraIssue> allIssues = new ArrayList<JiraIssue>();
		for (String csvPath : csvPaths.split(";")) {
			List<JiraIssue> containsDevelopers = parseForAttributeWithValue(csvPath, JiraAttribute.CUSTOM_FIELD_ASSIGNED_DEVELOPER, developerName);
			for (JiraIssue jiraIssue : containsDevelopers) {
				List<WorkLog> workLogs = 
						jiraIssueMapper.createWorkLogs(jiraIssue.getWorkLog());
				// STOPPED HERE. THiS CLASS WON'T BE USED.......
				
			}
		}
				parseAll(csvPaths);
		
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
	}*/
	
	
	/**
	 * Parses jira issues file(s) associated with the csvPaths.
	 * Takes a single path or multiple ';' delimited paths.
	 * @param csvPaths The path(s) of the files to parse.
	 * @return A list of jira issues.
	 */
	@Deprecated
	public List<JiraIssue> parseAll(String csvPaths) {
		List<JiraIssue> jiraIssues = new ArrayList<JiraIssue>();
		
		for (String csvPath : csvPaths.split(";")) {
			String path = FileUtil.getAbsolutePath(csvPath);
			List<JiraIssue> issuesToAdd = parse(path);
			jiraIssues.addAll(issuesToAdd);
		}
		
		
		return jiraIssues;
	}
	
	/*private List<JiraIssue> parseForAttributeWithValue(String csvPath, JiraAttribute attribute, String value) {
		List<JiraIssue> jiraIssues = new ArrayList<JiraIssue>();
		
		Collection<CSVRecord> records = csvFileParser.parseCsvFileReader(csvPath, false);
		
		int index = 0;
		Map<Integer, JiraAttribute> indexToAttributeMap = null;
		for(CSVRecord record : records){
			if (index == HEADER_ROW_INDEX) {
				// process the header row. have to do this because some column headers repeat.
				indexToAttributeMap = createIndexToAttributeMap(record);
			}
			else {
				//int attributeIndex = attributeNameToIndexMap.get(attribute);
				String valueFromRecord = record.getSingleValueFor(attribute);
				if (value.equals(valueFromRecord)) {
					JiraIssue jiraIssue = mapToJiraIssue(record, indexToAttributeMap);
					jiraIssues.add(jiraIssue);
				}
			}
			++index;
		}
		
		return jiraIssues;
	}	*/
	
	
	/**
	 * Parses the file at the given path. Returns jira issues where each attribute
	 * in the given map contains the given values;
	 * @param csvPath
	 * @param attributeToListOfValuesMap
	 * @return
	 *//*
	private List<JiraIssue> filterFor(String csvPath, Map<JiraAttribute, List<String>> attributeToListOfValuesMap ) {
		List<JiraIssue> jiraIssues = new ArrayList<JiraIssue>();
		
		Iterable<CSVRecord> records = csvFileParser.parseCsvFileReader(csvPath, false);
		
		Map<JiraAttribute, List<Integer>> attributeToListOfIndexesMap = null;
		Map<Integer, JiraAttribute> indexToAttributeMap = null;
		
		int index = HEADER_ROW_INDEX;
		for(CSVRecord record : records){
			if (index == HEADER_ROW_INDEX) {
				// process the header row. have to do this because some column headers repeat.
				attributeToListOfIndexesMap = createAttributeToListOfIndexesMap(record);
				indexToAttributeMap = createIndexToAttributeMap(record);
			}
			else {
				boolean addRecord = recordContainsAllValuesForAllAttributes(record,  
						attributeToListOfIndexesMap, attributeToListOfValuesMap );
				
				if (addRecord) {
					JiraIssue jiraIssue = mapToJiraIssue(record, indexToAttributeMap);
					jiraIssues.add(jiraIssue);
				}
			}
			++index;
		}
		
		return jiraIssues;
	}*/
	
	
	/*private boolean recordContainsAllValuesForAllAttributes(CSVRecord record,  
			Map<JiraAttribute, List<Integer>> attributeToListOfIndexesMap,
			Map<JiraAttribute, List<String>> attributeToListOfValuesMap ) {
		
		Set<JiraAttribute> attributes = attributeToListOfValuesMap.keySet();
		for (JiraAttribute attribute : attributes) {			
			List<String> values = attributeToListOfValuesMap.get(attribute);
			List<Integer> attributeIndexes = attributeToListOfIndexesMap.get(attribute);
			
			boolean recordContainsAllValuesForAttribute = 
					recordContainsAllValuesForAttribute(record, values, attributeIndexes);
			
			if (recordContainsAllValuesForAttribute == false) {
				return false;
			}
		}
		return true;
	}*/
	
	
	/**
	 * Returns true if ANY of the given values were found in any of the given indexes in the record.
	 * @param record the record to check
	 * @param filterValues the values 
	 * @param attributeIndexes
	 * @return
	 */
	/*private boolean recordContainsAllValuesForAttribute(
			CSVRecord record, List<String> values, List<Integer> attributeIndexes) {
		
		for (String value : values) {
			// iterate over all the indexes in the record checking for value
			boolean valueFound = valueFound(record, value, attributeIndexes);
			if (valueFound == false) {
				return false;
			}
		}
		return true;
	}*/
	
	/*private boolean valueFound(CSVRecord record, String filterValue, List<Integer> attributeIndexes) {
		for (Integer index : attributeIndexes) {
			if (filterValue.equals(record.get(index))) {
				return true;
			}
		}
		return false;
	}*/
	
	private List<JiraIssue> parse(String csvPath) {
		List<JiraIssue> jiraIssues = new ArrayList<JiraIssue>();
		//log.info("Attempting to parse " + csvPath + "...");
		Iterable<CSVRecord> records = csvFileParser.parseCsvFileReader(csvPath, false);
		
		//Iterable<CSVRecord> records = csvFileParser.parse(csvPath, false);
		
		int index = 0;
		Map<Integer, JiraAttribute> indexToAttributeMap = null;
		for(CSVRecord record : records){
			if (index == HEADER_ROW_INDEX) {
				// process the header row. have to do this because some column headers repeat.
				//log.debug("Creating index to attribute map.");
				indexToAttributeMap = createIndexToAttributeMap(record);
			}
			else {
				JiraIssue jiraIssue = mapToJiraIssue(record, indexToAttributeMap);
				jiraIssues.add(jiraIssue);
			}
			++index;
		}
		
		return jiraIssues;
	}
	
	/**
	 * Creates a JiraIssue from a CSVRecord. iterates over the record and puts all values
	 * not blank into the issue. 
	 * @param record The record to map to a jira issue.
	 * @param indexToAttributeMap Used to look up a value by index.
	 * @return a jira issue representation of the record without empty values.
	 */
	private JiraIssue mapToJiraIssue(CSVRecord record, Map<Integer, JiraAttribute> indexToAttributeMap) {
		JiraIssue jiraIssue = new JiraIssue();
		
		for (int i = 0; i < record.size(); i++) {
			JiraAttribute attribute = indexToAttributeMap.get(i);
			String value = record.get(i);
			
			if(StringUtils.isNotBlank(value)) {
				jiraIssue.addAttribute(attribute, value);
			}
		}
		
		return jiraIssue;
	}
	
	
	private Map<Integer, JiraAttribute> createIndexToAttributeMap(CSVRecord record) {
		Map<Integer, JiraAttribute> indexToAttributeMap = null;
		
		int numOfCellsInRow = record.size();
		
		indexToAttributeMap = new HashMap<Integer, JiraAttribute>(numOfCellsInRow, 1.0f);
		
		//log.debug("creating indexToAttributeMap for record: {} with size {}", record, record.size());
		int index = 0;
		for (String attributeName : record) {
			JiraAttribute ja = JiraAttribute.get(attributeName);
			if (ja == null) {
				throw new RuntimeException("Cannot create JiraAttribute given attributeName = '" + attributeName + "' in index: " + index);
			}
			//log.debug("indexToAttributeMap.put({}, {})", index, JiraAttribute.get(attributeName));
			indexToAttributeMap.put(index, JiraAttribute.get(attributeName));
			++index;
		}
		
		return indexToAttributeMap;
	}		
	
	
	/*private Map<JiraAttribute, List<Integer>> createAttributeToListOfIndexesMap(CSVRecord record) {		
		Map<JiraAttribute, List<Integer>> attributeToListOfIndexesMap = 
				new HashMap<JiraAttribute, List<Integer>>(record.size(), 1.0f);
		
		int index = 0;
		for (String attributeName : record) {
			JiraAttribute attribute = JiraAttribute.get(attributeName);
			if (attributeToListOfIndexesMap.containsKey(attribute)) {
				List<Integer> indexes = attributeToListOfIndexesMap.get(attribute);
				indexes.add(index);
			}
			else {
				List<Integer> indexes = new ArrayList<Integer>();
				indexes.add(index);
				attributeToListOfIndexesMap.put(attribute, indexes);
			}
		}
		
		return attributeToListOfIndexesMap;
	}*/
}
