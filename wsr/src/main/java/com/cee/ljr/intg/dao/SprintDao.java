package com.cee.ljr.intg.dao;

import java.util.Collection;
import java.util.List;

import com.cee.ljr.domain.common.Sprint;

public interface SprintDao {
	
	List<Sprint> getAll();
	
	List<Sprint> getByNumber(int sprintNumber);
	
	List<Sprint> getAllByNames(Collection<String> sprintNames);
	
	List<Sprint> getByKeys(Collection<String> sprintKeys);
	
	Sprint getByName(String sprintName);
	
	
	
}
