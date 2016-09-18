package com.cee.ljr.domain.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cee.ljr.utils.DateUtil;

public class SprintFactory {
	private static Map<String,ProjectSprint> nameToSprintMap;
	
	private static String[] pacafSprintData = {
		"PACAF Sprint 1; 04/Feb/16 4:51 PM; 19/Feb/16 10:10 AM",
		"PACAF Sprint 2; 23/Feb/16 11:11 AM; 10/Mar/16 4:33 PM",
		"PACAF Sprint 3; 11/Mar/16 5:00 AM; 29/Mar/16 4:32 PM",
		"PACAF Sprint 4; 30/Mar/16 7:11 AM; 15/Apr/16 2:16 PM",
		"PACAF Sprint 5; 15/Apr/16 5:00 AM; 15/Apr/16 5:00 AM",
		"PACAF Sprint 6; 29/Apr/16 5:30 AM; 18/May/16 10:43 AM",
		"PACAF Sprint 7; 17/May/16 5:30 AM; 03/Jun/16 8:32 AM",
		"PACAF Sprint 8; 03/Jun/16 6:00 AM; 22/Jun/16 7:18 AM",
		"PACAF Sprint 9; 21/Jun/16 7:22 AM;  08/Jul/16 9:38 AM",
		"PACAF Sprint 10; 08/Jul/16 5:30 AM; 26/Jul/16 3:23 PM",
		"PACAF Sprint 11; 26/Jul/16 5:30 AM; 11/Aug/16 10:44 AM",
		"PACAF Sprint 12; 11/Aug/16 5:30 AM; 29/Aug/16 12:59 PM",
		"PACAF Sprint 13; 29/Aug/16 5:00 AM; 16/Sep/16 5:48 AM",
		"PACAF Sprint 14; 15/Sep/16 5:30 AM; 03/Oct/16 6:27 AM"};
	
	
	public List<ProjectSprint> getSprintsByNumber(int number) {
		List<ProjectSprint> sprintList = new ArrayList<ProjectSprint>();
		
		for (String key : nameToSprintMap.keySet()) {
			int keyNumber = Integer.parseInt(StringUtils.split(key, "Sprint ")[1]);
			if (keyNumber == number) {
				sprintList.add(nameToSprintMap.get(key));
			}
		}
		
		return sprintList;
	}
	
	private static void init() {
		initialize(pacafSprintData);
	}
	
	
	private static void initialize(String[] sprintDataArray) {
		if (nameToSprintMap == null) {
			nameToSprintMap = new HashMap<String, ProjectSprint>();
		}
		for (String sprintData : sprintDataArray) {
			String[] data = sprintData.split(";");
			String name = data[0];
			Date startDate = DateUtil.getSprintDate(data[1].trim());
			Date endDate = DateUtil.getSprintDate(data[2].trim());
			nameToSprintMap.put(name, new ProjectSprint(name, startDate, endDate));
		}
	}
	
}
