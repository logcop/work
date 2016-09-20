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
		....need to make checks here, for some reason the wsr generator is giving this bad value...
		String numberStr = sprintName.substring(sprintName.lastIndexOf("Sprint ") + "Sprint ".length());
		
		if (!NumberUtils.isDigits(numberStr)) {
			throw new IllegalArgumentException("sprintName is invalid. Must be in the following format: [project name] Sprint [#]");
		}
		
		return new Integer(numberStr);
	}
}
