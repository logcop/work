package com.cee.ljr.intg.dao.impl;

import java.util.List;

import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.intg.dao.DeveloperDao;
import com.cee.ljr.intg.fileparser.DeveloperFileParser;


public class CsvDeveloperDao implements DeveloperDao {

	
	private String url;
	
	private DeveloperFileParser developerFileParser;
	
	@Override
	public List<Developer> getAll() {
		return developerFileParser.parseAll();
	}
}
