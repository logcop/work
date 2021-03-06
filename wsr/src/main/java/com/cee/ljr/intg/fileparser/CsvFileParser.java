package com.cee.ljr.intg.fileparser;

import java.io.Reader;

import com.cee.file.csv.criteria.Criteria;


public interface CsvFileParser <T> {
	
	Iterable<T> parse(String filePath, boolean skipHeader);
	
	Iterable<T> parse(String filePath, Criteria criteria);
	
	T parseForSingleRecord(String filePath, Criteria criteria);
	
	void closeReader(Reader reader);
	
}
