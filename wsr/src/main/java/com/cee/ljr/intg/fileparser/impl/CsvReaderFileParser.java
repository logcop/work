package com.cee.ljr.intg.fileparser.impl;

import org.springframework.stereotype.Component;

@Component
public class CsvReaderFileParser  {
	
	/*@Override
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
		
		CSVRecord record = null;
		if (records.iterator().hasNext()) {			
			record = records.iterator().next();
		}
		
		closeReader(reader);
		
		return record;
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
	}*/
}
