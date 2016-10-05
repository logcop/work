package com.cee.ljr.intg.fileparser;

import java.util.Collection;
import java.util.Date;

public interface SprintFileParser<T> {

	Iterable<T> parseAll();
	
	Iterable<T> parseByNumber(int sprintNumber);
	
	Iterable<T> parseByNames(Collection<String> sprintNames);
	
	Iterable<T> parseBetweenDates(Date beginDate, Date endDate);
	
	Iterable<T> parseByKeys(Collection<String> sprintKeys);
	
	T parseByName(String sprintName);
}
