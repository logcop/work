package com.cee.ljr.intg.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.intg.dao.DeveloperDao;
import com.cee.ljr.intg.fileparser.DeveloperFileParser;

@Component
public class DeveloperCsvDao implements DeveloperDao {
	
	@Autowired
	private DeveloperFileParser developerFileParser;
	
	@Override
	public List<Developer> getAll() {
		return developerFileParser.parseAll();
	}
	
	public Developer getByNameInJira(String nameInJira) {
		return developerFileParser.parseForName(nameInJira);
	}
}
