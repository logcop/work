/**
 * 
 */
package com.cee.ljr.intg.fileparser.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;

import com.cee.file.csv.CSVFormat;
import com.cee.file.csv.CSVRecord;
import com.cee.file.csv.criteria.Criteria;
import com.cee.ljr.utils.FileUtil;

/**
 * @author chuck
 *
 */
@PropertySource("classpath:/properties/data-access.properties")
public abstract class BaseCsvFileParser<T> {
	
	private boolean withHeader = true;
	
	private static final Logger log = LoggerFactory.getLogger(BaseCsvFileParser.class); 

	
	protected BaseCsvFileParser(boolean withHeader) {
		this.withHeader = withHeader;
	}
	
	protected abstract List<T> parseForAll(String filePaths, Criteria criteria);
	
	protected abstract List<T> parseForAll(String filePaths);
	
	protected abstract T parseForSingle(String filePaths, Criteria criteria);
	
	protected CSVRecord parseForSingleRecord(Reader reader, Criteria criteria) {
		CSVRecord record = null;
		
		Iterable<CSVRecord> records = parseForAllRecords(reader, criteria);
		
		if (records.iterator().hasNext()) {
			record = records.iterator().next();
		}
		
		return record;
	}
	
	
	protected CSVRecord parseForSingleRecord(Reader reader) {
		CSVRecord record = null;
		
		Iterable<CSVRecord> records = parseForAllRecords(reader);
		
		if (records.iterator().hasNext()) {
			record = records.iterator().next();
		}
		
		return record;
	}
	
	
	protected Iterable<CSVRecord> parseForAllRecords(Reader reader, Criteria criteria) {
		Iterable<CSVRecord> records = null;
		
		CSVFormat format = getFormat();
		
		try {
			records = format.parse(reader, criteria);
		} 
		catch (IOException ioe) {
			log.error("Unable to create records.", ioe);
		}
		
		return records;
	}
	
	
	protected Iterable<CSVRecord> parseForAllRecords(Reader reader) {
		Iterable<CSVRecord> records = null;
		
		CSVFormat format = getFormat();
		
		try {
			records = format.parse(reader);
		} 
		catch (IOException ioe) {
			log.error("Unable to create records.", ioe);
		}
		
		return records;
	}
	
	
	protected void closeReader(Reader reader) {
		try {
			reader.close();
		} catch(IOException ioe) {
			log.error("Error closing reader: " + reader, ioe);
		}
	}
	
	
	protected Reader getFileReader(String filePath) {
		Reader reader = null;
		String absolutePath = FileUtil.getAbsolutePath(filePath);
		try {
			reader = new FileReader(absolutePath);
		} 
		catch (FileNotFoundException fnfe) {
			log.error("Could not find file: " + filePath, fnfe);
			//closeReader(reader);
		}
		return reader;
	}
	
	protected CSVFormat getFormat() {
		if (withHeader) {
			return CSVFormat.EXCEL.withHeader();
		}
		
		return CSVFormat.EXCEL;
	}

}
