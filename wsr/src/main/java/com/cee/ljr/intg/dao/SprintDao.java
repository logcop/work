package com.cee.ljr.intg.dao;

import java.util.List;

import com.cee.ljr.domain.common.Sprint;

public interface SprintDao {
	
	List<Sprint> getAll();
	
	List<Sprint> getByNumber(int sprintNumber);
	
	List<Sprint> getAllByNames(List<String> sprintNames);
	
	Sprint getByName(String sprintName);
	
	
	
}
