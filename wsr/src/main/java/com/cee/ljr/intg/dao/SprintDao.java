package com.cee.ljr.intg.dao;

import java.util.Collection;
import java.util.Set;

import com.cee.ljr.domain.common.Sprint;

public interface SprintDao {
	
	Set<Sprint> getAll();
	
	Set<Sprint> getByNumber(int sprintNumber);
	
	Set<Sprint> getAllByNames(Collection<String> sprintNames);
	
	Set<Sprint> getByKeys(Collection<String> sprintKeys);
	
	Sprint getByName(String sprintName);
	
	
	
}
