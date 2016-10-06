package com.cee.ljr.intg.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.domain.common.Epic;
import com.cee.ljr.intg.jira.domain.JiraAttribute;

@Component
public class EpicMapper {

	public Epic map(CSVRecord epicRecord) {
		String key = epicRecord.getSingleValueFor(JiraAttribute.ISSUE_KEY);
		String name = epicRecord.getSingleValueFor(JiraAttribute.CUSTOM_FIELD_EPIC_NAME);
		
		return new Epic(key, name);
	}
	
	
	public List<Epic> map(Iterable<CSVRecord> epicRecords) {
		List<Epic> epics = new ArrayList<Epic>();
		
		for (CSVRecord epicRecord : epicRecords) {
			epics.add(map(epicRecord));
		}
		
		return epics;
	
	}

}
