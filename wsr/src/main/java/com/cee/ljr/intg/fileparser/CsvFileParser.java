package com.cee.ljr.intg.fileparser;

import com.cee.file.csv.criteria.Criteria;


public interface CsvFileParser <T> {
	
	Iterable<T> parse(String filePath, boolean skipHeader);
	
	Iterable<T> parse(String filePath, Criteria criteria);
	
}
