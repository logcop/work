package com.cee.wsr.properties;

import com.cee.wsr.utils.DateUtil;

public final class ApplicationPropertiesValidator {

	public static void validateDate(String dateString) {
		DateUtil.toDate(dateString);
	}

	public static void validateNumber(String number) {
		if (Integer.getInteger(number) == null) {

		}
	}

	public static void validateString(String string) {

	}
}
