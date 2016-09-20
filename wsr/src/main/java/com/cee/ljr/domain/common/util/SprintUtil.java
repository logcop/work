package com.cee.ljr.domain.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.cee.ljr.domain.common.Sprint;

public class SprintUtil {

	/**
	 * Parses the sprint number from the sprint name.<br/>
	 * The sprint name must be in the following format: [project name] Sprint [#]
	 * @param sprintName the sprint name to parse.
	 * @return the sprint number.
	 */
	public static Integer getNumberFromName(String sprintName) {
		if (sprintName == null) {
			throw new IllegalArgumentException("sprintName must not be null");
		}
		
		int sprintNumberBeginIndex = sprintName.lastIndexOf("Sprint ") + "Sprint ".length();
		
		if (sprintNumberBeginIndex <= 0 ) {
			throw new IllegalArgumentException("sprintName [" + sprintName 
					+ "] is invalid. Must be in the following format: [project name] Sprint [#]");
		}		

		String numberStr = "";
		try {
			numberStr = sprintName.substring(sprintNumberBeginIndex);
		} catch (StringIndexOutOfBoundsException siobe) {
			throw new IllegalArgumentException("sprintName [" + sprintName 
					+ "] is invalid. Must be in the following format: [project name] Sprint [#]");
		}
		
		if (!NumberUtils.isDigits(numberStr)) {
			throw new IllegalArgumentException("sprintName [" + sprintName 
					+ "] is invalid. Must be in the following format: [project name] Sprint [#]");
		}
		
		return new Integer(numberStr);
	}
	
	public static List<String> getSprintNames(List<Sprint> sprints) {
		List<String> names = new ArrayList<String>();
		
		for (Sprint sprint : sprints) {
			names.add(sprint.getName());
		}
		
		return names;
	}
}
