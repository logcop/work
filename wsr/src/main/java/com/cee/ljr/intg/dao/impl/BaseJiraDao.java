package com.cee.ljr.intg.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/properties/data-access.properties")
public class BaseJiraDao {

	@Autowired
	DeveloperCsvDao developerCsvDao;
	
	@Autowired
	SprintCsvDao sprintCsvDao;
	

}
