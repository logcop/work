package com.cee.ljr.intg.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.ljr.domain.common.Epic;
import com.cee.ljr.intg.dao.EpicDao;
import com.cee.ljr.intg.fileparser.impl.EpicCsvFileParser;

@Component
public class EpicCsvDao implements EpicDao {
	
	@Autowired
	EpicCsvFileParser fileParser;
	
	@Override
	public List<Epic> getByKeys(String csvPaths, List<String> keys) {		
		return fileParser.parseByKeys(csvPaths, keys);
	}
	
	@Override
	public Epic getByKey(String csvPaths, String key) {
		return fileParser.parseByKey(csvPaths, key);
	}
}
