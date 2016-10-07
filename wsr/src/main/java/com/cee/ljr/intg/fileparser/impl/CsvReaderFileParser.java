package com.cee.ljr.intg.fileparser.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVFormat;
import com.cee.file.csv.CSVRecord;
import com.cee.file.csv.criteria.Criteria;
import com.cee.ljr.intg.jira.domain.JiraIssue;

@Component
public class CsvReaderFileParser extends BaseCsvFileParser<JiraIssue>  {
	private static final Logger log = LoggerFactory.getLogger(CsvReaderFileParser.class);
	
	private static final boolean WITH_HEADER = true;
	
	public CsvReaderFileParser() {
		super(WITH_HEADER);
	}
	
	/*public Iterable<CSVRecord> parse(String filePath, boolean parseHeader) {
		String path = FileUtil.getAbsolutePath(filePath);
		
		Reader reader = getFileReader(path);		
		
		Iterable<CSVRecord> records = parseCsvFileReader(reader, parseHeader);
		
		//closeReader(reader);
		
		return records;		
	}*/
	

	/*public Iterable<CSVRecord> parse(String filePath, Criteria criteria) {
		String path = FileUtil.getAbsolutePath(filePath);
		
		Reader reader = getFileReader(path);
		
		Iterable<CSVRecord> records = parseCsvFileReader(reader, criteria);
		
		//closeReader(reader);
		
		return records;
	}*/
	
	
	/*public CSVRecord parseForSingleRecord(String filePath, Criteria criteria) {
		String path = FileUtil.getAbsolutePath(filePath);
		
		Reader reader = getFileReader(path);
		
		Iterable<CSVRecord> records = parseCsvFileReader(reader, criteria);
		
		CSVRecord record = null;
		if (records.iterator().hasNext()) {			
			record = records.iterator().next();
		}
		
		//closeReader(reader);
		
		return record;
	}*/
	
	public Iterable<CSVRecord> parseCsvFileReader(Reader reader, Criteria criteria) {
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
	
	
	public Collection<CSVRecord> parseCsvFileReader(String filePaths, boolean parseHeader) {
		List<CSVRecord> recordList = new ArrayList<CSVRecord>();
		
		for (String filePath : filePaths.split(";")) {
			Reader reader = getFileReader(filePath);
			
			CSVFormat csvFormat = getFormat();
			try {
				Iterable<CSVRecord> records = csvFormat.parse(reader);
				recordList.addAll(convertToList(records));
			} 
			catch (IOException ioe) {
				log.error("Unable to create records.", ioe);
			}
		}
		
		
		
		return recordList;
	}

	@Override
	protected List<JiraIssue> parseForAll(String filePaths, Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<JiraIssue> parseForAll(String filePaths) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JiraIssue parseForSingle(String filePaths, Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
