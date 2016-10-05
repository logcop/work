package com.cee.ljr.intg.mapping;

import java.util.Set;
import java.util.TreeSet;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.intg.fileparser.impl.SprintHeader;

public abstract class SprintMapper {

	public static Sprint mapRecord(CSVRecord sprintRecord) {
		String name = sprintRecord.getSingleValueFor(SprintHeader.SPRINT_NAME);
		String startDateStr = sprintRecord.getSingleValueFor(SprintHeader.START_DATE);
		String endDateStr = sprintRecord.getSingleValueFor(SprintHeader.END_DATE);
		
		return new Sprint(name, Sprint.getSprintDate(startDateStr), Sprint.getSprintDate(endDateStr));
	}
	
	
	public static Set<Sprint> mapRecords(Iterable<CSVRecord> sprintRecords) {
		Set<Sprint> sprints = new TreeSet<Sprint>();
		
		for (CSVRecord sprintRecord : sprintRecords) {
				sprints.add(mapRecord(sprintRecord));
		}
		
		return sprints;
	
	}

}
