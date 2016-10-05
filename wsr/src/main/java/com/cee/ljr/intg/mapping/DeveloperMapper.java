/**
 * 
 */
package com.cee.ljr.intg.mapping;

import java.util.Set;
import java.util.TreeSet;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.intg.fileparser.impl.DeveloperHeader;

/**
 * @author chuck
 *
 */
public class DeveloperMapper {

	public Set<Developer> map(Iterable<CSVRecord> records) {
		Set<Developer> developers = new TreeSet<Developer>();
		
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
