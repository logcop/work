package com.cee.ljr.intg.fileparser;

import java.util.Date;
import java.util.List;

import com.cee.file.csv.CSVRecord;

public interface SprintFileParser<T> {

	Iterable<T> parseAll();
	
	Iterable<T> parseByNumber(int sprintNumber);
	
	Iterable<T> parseByNames(List<String> sprintNames);
	
	Iterable<CSVRecord> parseDateBetweenSprintStartAndEnd(Date date);
	
	T parseByName(String sprintName);
}
