package com.cee.ljr.intg.dao;

import java.util.List;

import com.cee.ljr.domain.common.Developer;

public interface DeveloperDao {
	
	List<Developer> getAll();
	
	Developer getByNameInJira(String nameInJira);
}
