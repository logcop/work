package com.cee.wsr.domain.jira;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.CollectionUtils;

public class JiraIssue {
	
	private Map<String, List<String>> attributeNameValuesMap = new HashMap<String, List<String>>(75,1.0f);
		

	public JiraIssue() {
		// TODO Auto-generated constructor stub
	}
	
	public void addAttribute(String name, List<String> values) {		
		if (attributeNameValuesMap.containsKey(name)) {
			attributeNameValuesMap.get(name).addAll(values);
		}
		else {
			attributeNameValuesMap.put(name, values);
		}
	}
	
	public void addAttribute(String name, String value) {
		if (attributeNameValuesMap.containsKey(name)) {
			List<String> values = attributeNameValuesMap.get(name);
			values.add(value);
		}
		else {
			List<String> values = new ArrayList<String>();
			values.add(value);
			attributeNameValuesMap.put(name, values);
		}
	}
	
	public String getValue(String name) {		
		String value = null;
		List<String> values = attributeNameValuesMap.get(name);
		if (!CollectionUtils.isEmpty(values)) {
			value = values.get(0);
		}
		if(value == null) {
			return "";
		}
		return value;
	}
	
	public List<String> getValues(String name) {
		List<String> values = attributeNameValuesMap.get(name);
		
		if (values == null) {
			return new ArrayList<String>();
		}
		
		return values;
	}
	
	
	public String getSummary() {
		return getValue(JiraAttribute.SUMMARY);
	}
	
	public String getKey() {
		return getValue(JiraAttribute.ISSUE_KEY);
	}
	
	public String getId() {
		return getValue(JiraAttribute.ISSUE_ID);
	}
	
	public String getParentId() {
		return getValue(JiraAttribute.PARENT_ID);
	}
	
	public String getType() {
		return getValue(JiraAttribute.ISSUE_TYPE);
	}
	
	public String getStatus() {
		return getValue(JiraAttribute.STATUS);
	}
	
	public String getProjectKey() {
		return getValue(JiraAttribute.PROJECT_KEY);
	}
	
	public String getProjectName() {
		return getValue(JiraAttribute.PROJECT_NAME);
	}
	
	public List<String> getSprints() {
		return getValues(JiraAttribute.SPRINT);
	}
	
	public String getEpicName() {
		return getValue(JiraAttribute.CUSTOM_FIELD_EPIC_NAME);
	}
	
	public String getEpicKey() {
		return getValue(JiraAttribute.CUSTOM_FIELD_EPIC_LINK);
	}
	
	public String getEstimate() {
		return getValue(JiraAttribute.ORIGINAL_ESTIMATE);
	}
	
	public String getTimeSpent() {
		return getValue(JiraAttribute.TIME_SPENT);
	}
	
	public List<String> getLinkedIssueKeys() {
		return getValues(JiraAttribute.OUTWARD_ISSUE_LINK_PROBLEM_INCIDENT);
	}
	
	public List<String> getDevelopers() {
		return getValues(JiraAttribute.CUSTOM_FIELD_ASSIGNED_DEVELOPER);
	}
	

	public String getStoryPoints() {
		return getValue(JiraAttribute.CUSTOM_FIELD_STORY_POINTS);
	}
	
	public List<String> getWorkLog() {
		return getValues(JiraAttribute.LOG_WORK);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {		
		return ToStringBuilder.reflectionToString(this);
	}
	
	

}
