package com.cee.ljr.intg.fileparser;

import java.util.List;

import com.cee.ljr.domain.common.Sprint;

public interface SprintFileParser {

	List<Sprint> parseAll();
	
	List<Sprint> parseByNumber(int sprintNumber);
	
	List<Sprint> parseByNames(List<String> sprintNames);
	
	Sprint parseByName(String sprintName);
}
