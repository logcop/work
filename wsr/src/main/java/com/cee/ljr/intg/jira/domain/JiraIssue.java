package com.cee.ljr.intg.jira.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class JiraIssue {
	private static final Logger log = LoggerFactory.getLogger(JiraIssue.class);
	
	private Map<JiraAttribute, List<String>> attributeToValuesMap = new HashMap<JiraAttribute, List<String>>(75,1.0f);
		

	public JiraIssue() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void addAttribute(JiraAttribute jiraAttribute, List<String> values) {
		
		if (attributeToValuesMap.containsKey(jiraAttribute)) {
			//log.debug("Adding values: {} to attribute: {}", values.toString(), jiraAttribute.toString());
			attributeToValuesMap.get(jiraAttribute).addAll(values);
		}
		else {
			//log.debug("Adding attribute: {} with values: ", jiraAttribute, values.toString());
			attributeToValuesMap.put(jiraAttribute, values);
		}
	}
	
	
	public void addAttribute(JiraAttribute jiraAttribute, String value) {
		if (attributeToValuesMap.containsKey(jiraAttribute)) {
			//log.debug("Adding value: {} to attribute: {}", value, jiraAttribute);
			List<String> values = attributeToValuesMap.get(jiraAttribute);
			values.add(value);
		}
		else {
			//log.debug("Adding attribute: {} with value: ", jiraAttribute, value);
			List<String> values = new ArrayList<String>();
			values.add(value);
			attributeToValuesMap.put(jiraAttribute, values);
		}
	}
	
	
	/**
	 * Gets the
	 * @param name
	 * @return
	 */
	public String getValue(JiraAttribute attribute) {		
		String value = null;
		List<String> values = attributeToValuesMap.get(attribute);
		if (!CollectionUtils.isEmpty(values)) {
			value = values.get(0);
		}
		if(value == null) {
			return "";
		}
		return value;
	}
	
	
	public Set<String> getDistinctValues(JiraAttribute attribute) {
		Set<String> valueSet = new TreeSet<String>();
		
		List<String> values = attributeToValuesMap.get(attribute);
		
		if(values != null) {			
			for (String value : values) {
				valueSet.add(value);
			}
		}
		
		return valueSet;
	}
	
	
	public List<String> getAllValues(JiraAttribute attribute) {
		List<String> values = attributeToValuesMap.get(attribute);
		
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
	
	
	public Set<String> getSprints() {
		return getDistinctValues(JiraAttribute.SPRINT);
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
		return getAllValues(JiraAttribute.OUTWARD_ISSUE_LINK_PROBLEM_INCIDENT);
	}
	
	
	public Set<String> getDevelopers() {
		return getDistinctValues(JiraAttribute.CUSTOM_FIELD_ASSIGNED_DEVELOPER);
	}
	

	public String getStoryPoints() {
		return getValue(JiraAttribute.CUSTOM_FIELD_STORY_POINTS);
	}
	
	
	public List<String> getWorkLog() {
		return getAllValues(JiraAttribute.LOG_WORK);
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
