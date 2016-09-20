package com.cee.ljr.domain.common.util;

import org.apache.commons.lang3.math.NumberUtils;

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
		
		if (sprintNumberBeginIndex <= 0) {
			throw new IllegalArgumentException("sprintName [" + sprintName + "] is invalid. Must be in the following format: [project name] Sprint [#]");
		}		

		String numberStr = sprintName.substring(sprintNumberBeginIndex);
		
		if (!NumberUtils.isDigits(numberStr)) {
			throw new IllegalArgumentException("sprintName [" + sprintName + "] is invalid. Must be in the following format: [project name] Sprint [#]");
		}
		
		return new Integer(numberStr);
	}
}
