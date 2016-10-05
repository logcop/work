package com.cee.ljr.intg.dao;

import java.util.Collection;
import java.util.Set;

import com.cee.ljr.domain.common.Developer;

public interface DeveloperDao {
	
	Set<Developer> getAll();
	
	Developer getByNameInJira(String nameInJira);
	
	Set<Developer> getByKeys(Collection<String> keys);
	
}
