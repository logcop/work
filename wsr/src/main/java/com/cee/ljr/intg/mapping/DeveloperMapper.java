/**
 * 
 */
package com.cee.ljr.intg.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.intg.fileparser.impl.DeveloperHeader;

/**
 * @author chuck
 *
 */
@Component
public class DeveloperMapper {

	public List<Developer> map(Iterable<CSVRecord> records) {
		List<Developer> developers = new ArrayList<Developer>();
		
		for (CSVRecord record : records) {
			Developer developer = map(record);
			developers.add(developer);
		}
		
		return developers;
	}
	
	public Developer map(CSVRecord record) {
		String key = record.getSingleValueFor(DeveloperHeader.KEY);
		String firstName = record.getSingleValueFor(DeveloperHeader.FIRST_NAME);
		String lastName = record.getSingleValueFor(DeveloperHeader.LAST_NAME);
		
		return new Developer(key, firstName, lastName);
	}

}
