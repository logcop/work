/**
 * 
 */
package com.cee.ljr.intg.fileparser.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
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
	
	protected boolean withHeader = true;
	
	private static final Logger log = LoggerFactory.getLogger(BaseCsvFileParser.class); 

	
	protected BaseCsvFileParser(boolean withHeader) {
		this.withHeader = withHeader;
	}
	
	protected abstract List<T> parseForAll(String filePaths, Criteria criteria);
	
	protected abstract List<T> parseForAll(String filePaths);
	
	protected abstract T parseForSingle(String filePaths, Criteria criteria);
	
	public CSVRecord parseForSingleRecord(String filePaths, Criteria criteria) {
		CSVRecord record = null;
		
		Collection<CSVRecord> records = parseForAllRecords(filePaths, criteria);
		
		for (CSVRecord csvRecord : records) {
			record = csvRecord;
			//log.debug("found record: " + record.toString());
			break;//just need the first one....
		}
		
		return record;
	}
	
	
	public CSVRecord parseForSingleRecord(String filePaths) {
		CSVRecord record = null;
		
		Collection<CSVRecord> records = parseForAllRecords(filePaths);
		
		for (CSVRecord csvRecord : records) {
			record = csvRecord;
			//log.debug("found record: " + record.toString());
			break;//just need the first one....
		}
		
		return record;
	}
	
	
	public Collection<CSVRecord> parseForAllRecords(String filePaths, Criteria criteria) {
		List<CSVRecord> recordList = new ArrayList<CSVRecord>();
		
		for (String filePath : filePaths.split(";")) {
			log.debug("parsing for all records in {} meeting the following criteria: [{}]", filePath, criteria);
			Iterable<CSVRecord> records = null;
			
			Reader reader = getFileReader(FileUtil.getAbsolutePath(filePath));
			
			CSVFormat format = getFormat();
			
			try {
				records = format.parse(reader, criteria);
			} 
			catch (IOException ioe) {
				throw new RuntimeException("Unable to create records.", ioe);
			}
			recordList.addAll(convertToList(records));
			
			closeReader(reader);
		}
		
		return recordList;
	}
	
	
	public Collection<CSVRecord> parseForAllRecords(String filePaths) {
		List<CSVRecord> recordList = new ArrayList<CSVRecord>();
		
		for (String filePath : filePaths.split(";")) {
			log.debug("parsing for all records in {}", filePath);
			Iterable<CSVRecord> records = null;
			
			Reader reader = getFileReader(FileUtil.getAbsolutePath(filePath));
			
			CSVFormat format = getFormat();
			
			try {
				records = format.parse(reader);
			} 
			catch (IOException ioe) {
				throw new RuntimeException("Unable to create records.", ioe);
			}
			recordList.addAll(convertToList(records));
			
			closeReader(reader);
		}
		
		return recordList;
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
			throw new RuntimeException("Could not find file: " + filePath, fnfe);
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
	
	protected List<CSVRecord> convertToList(Iterable<CSVRecord> records) {
		List<CSVRecord> recordList = new ArrayList<CSVRecord>();
		
		for (CSVRecord csvRecord : records) {
			recordList.add(csvRecord);
		}
		
		return recordList;
	}

}
