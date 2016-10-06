package com.cee.ljr.intg.fileparser;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.cee.ljr.domain.common.Sprint;

public interface SprintFileParser {

	List<Sprint> parseAll();
	
	List<Sprint> parseByNumber(int sprintNumber);
	
	List<Sprint> parseByNames(Collection<String> sprintNames);
	
	List<Sprint> parseBetweenDates(Date beginDate, Date endDate);
	
	List<Sprint> parseByKeys(Collection<String> sprintKeys);
	
	Sprint parseByName(String sprintName);
}
