package com.cee.ljr.intg.fileparser.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cee.ljr.intg.fileparser.CsvFileParser;
import com.cee.ljr.utils.FileUtil;

@Component
public class CsvReaderFileParser implements CsvFileParser<CSVRecord> {
	private static final Logger log = LoggerFactory.getLogger(CsvReaderFileParser.class); 
	
	@Override
	public Iterable<CSVRecord> parse(String filePath, boolean hasHeader) {
		String path = FileUtil.getAbsolutePath(filePath);
		
		Reader reader = null;
		
		try {
			reader = new FileReader(path);
		} catch (FileNotFoundException fnfe) {
			log.error("Could not find file: " + path, fnfe);
		}
		
		CSVFormat csvFormat = getFormat(hasHeader);
		try {
			Iterable<CSVRecord> records = csvFormat.parse(reader);
			return records;
		} catch (IOException ioe) {
			log.error("Unable to parse file: " + path, ioe);
		}
		
		return null;
		
	}
	
	private CSVFormat getFormat(boolean hasHeader) {
		if (hasHeader) {
			return CSVFormat.EXCEL.withHeader();
		}
		
		return CSVFormat.RFC4180;
	}
}
