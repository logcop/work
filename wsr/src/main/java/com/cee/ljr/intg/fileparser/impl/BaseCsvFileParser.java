/**
 * 
 */
package com.cee.ljr.intg.fileparser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.intg.fileparser.CsvFileParser;

/**
 * @author chuck
 *
 */
@PropertySource("classpath:/properties/data-access.properties")
public abstract class BaseCsvFileParser {


	@Autowired
	CsvFileParser<CSVRecord> csvFileParser;	

}
