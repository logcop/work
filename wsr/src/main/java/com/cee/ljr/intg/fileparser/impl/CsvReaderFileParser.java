package com.cee.ljr.intg.fileparser.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVFormat;
import com.cee.file.csv.CSVRecord;
import com.cee.file.csv.criteria.Criteria;
import com.cee.ljr.intg.fileparser.CsvFileParser;
import com.cee.ljr.utils.FileUtil;

@Component
public class CsvReaderFileParser implements CsvFileParser<CSVRecord> {
	
	private static final Logger log = LoggerFactory.getLogger(CsvReaderFileParser.class); 
	
	@Override
	public Iterable<CSVRecord> parse(String filePath, boolean parseHeader) {
		String path = FileUtil.getAbsolutePath(filePath);
		
		Reader reader = createFileReader(path);		
		
		Iterable<CSVRecord> records = parseCsvFileReader(reader, parseHeader);
		
		closeReader(reader);
		
		return records;		
	}
	

	@Override
	public Iterable<CSVRecord> parse(String filePath, Criteria criteria) {
		String path = FileUtil.getAbsolutePath(filePath);
		
		Reader reader = createFileReader(path);
		
		Iterable<CSVRecord> records = parseCsvFileReader(reader, criteria);
		
		closeReader(reader);
		
		return records;
	}
	
	@Override
	public CSVRecord parseForSingleRecord(String filePath, Criteria criteria) {
		String path = FileUtil.getAbsolutePath(filePath);
		
		Reader reader = createFileReader(path);
		
		Iterable<CSVRecord> records = parseCsvFileReader(reader, criteria);
		
		closeReader(reader);
		
		if (records.iterator().hasNext()) {
			return records.iterator().next();
		}
		
		return null;
	}
	
	private Iterable<CSVRecord> parseCsvFileReader(Reader reader, Criteria criteria) {
		Iterable<CSVRecord> records = null;
		
		CSVFormat csvFormat = CSVFormat.EXCEL.withHeader();
		try {
			records = csvFormat.parse(reader, criteria);
		} 
		catch (IOException ioe) {
			log.error("Unable to create records.", ioe);
		}
		
		return records;
	}
	
	
	private Iterable<CSVRecord> parseCsvFileReader(Reader reader, boolean parseHeader) {
		Iterable<CSVRecord> records = null;
		
		CSVFormat csvFormat = getFormat(parseHeader);
		try {
			records = csvFormat.parse(reader);
		} 
		catch (IOException ioe) {
			log.error("Unable to create records.", ioe);
		}
		
		return records;
	}
	
	
	private Reader createFileReader(String filePath) {
		Reader reader = null;
		try {
			reader = new FileReader(filePath);
		} catch (FileNotFoundException fnfe) {
			log.error("Could not find file: " + filePath, fnfe);
			closeReader(reader);
		}
		return reader;
	}
	
	
	protected void closeReader(Reader reader) {
		try {
			reader.close();
		} catch(IOException ioe) {
			log.error("Error closing reader: " + reader, ioe);
		}
	}
	
	
	private CSVFormat getFormat(boolean parseHeader) {
		if (parseHeader) {
			return CSVFormat.EXCEL.withHeader();
		}
		
		return CSVFormat.EXCEL;
	}
}
