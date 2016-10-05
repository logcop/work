package com.cee.ljr.intg.mapping;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.domain.common.Epic;
import com.cee.ljr.intg.jira.domain.JiraAttribute;

@Component
public class EpicMapper {

	public static Epic mapRecord(CSVRecord epicRecord) {
		String key = epicRecord.getSingleValueFor(JiraAttribute.ISSUE_KEY);
		String name = epicRecord.getSingleValueFor(JiraAttribute.CUSTOM_FIELD_EPIC_NAME);
		
		return new Epic(key, name);
	}
	
	
	public static Set<Epic> mapRecords(Iterable<CSVRecord> epicRecords) {
		Set<Epic> epics = new HashSet<Epic>();
		
		for (CSVRecord epicRecord : epicRecords) {
			epics.add(mapRecord(epicRecord));
		}
		
		return epics;
	
	}

}
