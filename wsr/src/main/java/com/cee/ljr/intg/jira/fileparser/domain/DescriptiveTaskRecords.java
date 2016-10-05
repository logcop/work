package com.cee.ljr.intg.jira.fileparser.domain;

import java.util.Iterator;
import java.util.Map;

import com.cee.file.csv.CSVRecord;

public class DescriptiveTaskRecords {

	Map<String, CSVRecord> issueKeyToTaskRecordMap;
	Map<String, CSVRecord> epicLinkToEpicRecordMap;
	//Map<>
	
	
	
	public DescriptiveTaskRecords(Iterator<CSVRecord> taskRecords) {
		
		
		// TODO Auto-generated constructor stub
	}

}
