package com.cee.wsr.utils;

import java.util.HashMap;
import java.util.Map;

public class DevNameUtil {
	private static Map<String, String> firstToFullNameMap = new HashMap<String, String>();

	static {
		firstToFullNameMap.put("Chuck", "Chuck Emmons");
		firstToFullNameMap.put("Jon", "Jon Davis");
		firstToFullNameMap.put("Peter", "Peter Justeson");
		firstToFullNameMap.put("Marc", "Marc Mendoza");
		firstToFullNameMap.put("Robert", "Robert Patch");
		firstToFullNameMap.put("Evan", "Evan Komiyama");
		firstToFullNameMap.put("Lewis", "Lewis Nakao");
		firstToFullNameMap.put("Jim", "Jim Hayes");
		firstToFullNameMap.put("Jordan", "Jordan Laimana");
		firstToFullNameMap.put("Sal", "Sal Fiesta");
	}

	private DevNameUtil() {
	}

	public static String getFullName(String firstName) {
		return firstToFullNameMap.get(firstName);
	}

}
