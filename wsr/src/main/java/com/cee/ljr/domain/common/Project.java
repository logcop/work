package com.cee.ljr.domain.common;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class Project {
	private static final Logger log = LoggerFactory.getLogger(Project.class);
	private String name;
	private String key;
	private Map<String, Epic> keyToEpicMap = new HashMap<String, Epic>();
	private double hoursWorkedBetween;
	private double totalHoursWorked;
	
	public Project(String key, String name) {
		this.key = key;
		this.name = name;
		hoursWorkedBetween = 0;
		totalHoursWorked = 0;
	}
	
	/**
	 * <b>WARNING: If this method is called and the results are greater than zero, it will not update again.
	 * Assumes this project is completely initialized and no more epics will be added.</b>
	 * This is to speed up document generation at the end. <br><br>
	 * Gets the total hours worked between the given dates. 
	 * @param startDate The start Date of the range to get the hours for.
	 * @param endDate The end Date of the range to get the hours for.
	 * @return The hours worked.
	 */
	public double getHoursWorkedBetween(Date startDate, Date endDate) {
		//log.debug("getting hours worked between {} and {}", startDate, endDate);
		if (hoursWorkedBetween <=0 ) {
			//log.debug("hoursWorkedBetween <=0");
			for (Epic epic : keyToEpicMap.values()) {
				double epicHoursWorkedBetween = epic.getHoursWorkedBetween(startDate, endDate);
				////log.debug("epic hours: {}", epicHoursWorkedBetween);
				hoursWorkedBetween += epicHoursWorkedBetween;
			}
		}
		//log.debug("returning hoursWorkedBetween: {}", hoursWorkedBetween);
		return hoursWorkedBetween;
	}
	
	public double getTimeSpentInHours() {
		double timeSpent = 0;
		for (Epic epic : keyToEpicMap.values()) {
			timeSpent += epic.getTimeSpentInHours();
		}
		return timeSpent;
	}
	
	public Epic getEpic(String epicKey) {
		return keyToEpicMap.get(epicKey);
	}
	
	public void addEpic(Epic epic) {
		
		if (epic == null) {
			throw new IllegalArgumentException("Task cannot be null.");
		}
		String epicKey = epic.getKey();
		if (epicKey == null) {
			throw new RuntimeException("Epic must contain a key.");
		}
		if (keyToEpicMap.containsKey(epicKey)) {
			throw new RuntimeException("Epic '" + epicKey + "' already exists in Project " + getName());
		}
		if (CollectionUtils.isEmpty(keyToEpicMap)) {
			keyToEpicMap = new HashMap<String, Epic>();
		}
		//log.debug("keyToEpicMap.put(\n{},\n{})", epicKey, epic);
		keyToEpicMap.put(epicKey, epic);
		
	}	
	
	public static String getProjectAbbr(String projectName) {
		String abbr = "";
		if (StringUtils.isNotBlank(projectName)) {
			int abbrPosition = 0;
			String[] nameSegmentedBySpace = StringUtils.split(projectName, ' ');
			if (StringUtils.isAllUpperCase(nameSegmentedBySpace[abbrPosition])) {
				// should be the correct acronym if it's all upper case.
				abbr = nameSegmentedBySpace[abbrPosition];
			}
		}
		return abbr;
	}
	
	public Collection<Epic> getEpics() {
		return keyToEpicMap.values();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	
	/**
	 * @return the totalHoursWorked
	 */
	public double getTotalHoursWorked() {
		if (totalHoursWorked <=0) {
			for (Epic epic : keyToEpicMap.values()) {
				double totalEpicHoursWorked = epic.getTotalHoursWorked();
				totalHoursWorked += totalEpicHoursWorked;
			}
		}
		return totalHoursWorked;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
