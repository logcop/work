package com.cee.ljr.intg.fileparser;

public interface CsvFileParser <T> {
	
	Iterable<T> parse(String filePath, boolean hasHeader);
	
}
