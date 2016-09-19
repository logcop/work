package com.cee.ljr.intg.fileparser.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseExcelCsvFileParser {
	private static final Logger log = LoggerFactory.getLogger(BaseExcelCsvFileParser.class); 
	
	protected Iterable<CSVRecord> parse(String filePath) {
		Path path = Paths.get(filePath);
		path.toAbsolutePath().toString();
		Iterable<CSVRecord> records = null;
		//InputStream is = 
		URI uri = new URI(filePath);//.getResource(filePath);
		File file = new File(uri);
		Reader reader = new FileReader(file);
		
		try {
			records = CSVFormat.RFC4180.parse(reader);
		} catch (IOException ioe) {
			log.error("Unable to parse file: " + filePath, ioe);
		}
		
		return records;
	}
}
