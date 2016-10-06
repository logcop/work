package com.cee.ljr.intg.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.intg.fileparser.impl.SprintHeader;

@Component
public class SprintMapper {

	public Sprint map(CSVRecord sprintRecord) {
		String name = sprintRecord.getSingleValueFor(SprintHeader.SPRINT_NAME);
		String startDateStr = sprintRecord.getSingleValueFor(SprintHeader.START_DATE);
		String endDateStr = sprintRecord.getSingleValueFor(SprintHeader.END_DATE);
		
		return new Sprint(name, Sprint.getSprintDate(startDateStr), Sprint.getSprintDate(endDateStr));
	}
	
	
	public List<Sprint> map(Iterable<CSVRecord> sprintRecords) {
		List<Sprint> sprints = new ArrayList<Sprint>();
		
		for (CSVRecord sprintRecord : sprintRecords) {
				sprints.add(map(sprintRecord));
		}
		
		return sprints;
	
	}

}
