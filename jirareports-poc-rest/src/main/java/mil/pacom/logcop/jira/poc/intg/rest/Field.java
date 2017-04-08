package mil.pacom.logcop.jira.poc.intg.rest;

import java.util.HashSet;
import java.util.Set;

public abstract class Field {
	
	public static final String ID = "id";
	public static final String ALL = "*all";
	public static final String WORKLOG = "worklog";
	public static final String SUMMARY = "summary";
	public static final String KEY = "key";
	public static final String TYPE = "issuetype";
	public static final String CREATED = "created";
	public static final String UPDATED = "updated";
	public static final String PROJECT = "project";
	public static final String STATUS = "status";
	
	private Field() {
		// TODO Auto-generated constructor stub
	}
	
	public static final Set<String> getAllRequired() {
		Set<String> requiredFields = new HashSet<String>();
		
		requiredFields.add(TYPE);
		requiredFields.add(SUMMARY);
		requiredFields.add(CREATED);
		requiredFields.add(UPDATED);
		requiredFields.add(PROJECT);
		requiredFields.add(STATUS);
		
		return requiredFields;
	}

}
