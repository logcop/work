package com.cee.ljr.domain.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cee.ljr.utils.DateUtil;

@Component
public class SprintFactory {
	//private static final Logger log = LoggerFactory.getLogger(SprintFactory.class);
	
	private static Map<String,Sprint> nameToSprintMap;
	
	private static final String[] pacafSprintData = {
		"PACAF Sprint 1;  04/Feb/16 4:51 PM;  19/Feb/16 10:10 AM",
		"PACAF Sprint 2;  23/Feb/16 11:11 AM; 10/Mar/16 4:33 PM",
		"PACAF Sprint 3;  11/Mar/16 5:00 AM;  29/Mar/16 4:32 PM",
		"PACAF Sprint 4;  30/Mar/16 7:11 AM;  15/Apr/16 2:16 PM",
		"PACAF Sprint 5;  15/Apr/16 5:00 AM;  15/Apr/16 5:00 AM",
		"PACAF Sprint 6;  29/Apr/16 5:30 AM;  18/May/16 10:43 AM",
		"PACAF Sprint 7;  17/May/16 5:30 AM;  03/Jun/16 8:32 AM",
		"PACAF Sprint 8;  03/Jun/16 6:00 AM;  22/Jun/16 7:18 AM",
		"PACAF Sprint 9;  21/Jun/16 7:22 AM;  08/Jul/16 9:38 AM",
		"PACAF Sprint 10; 08/Jul/16 5:30 AM;  26/Jul/16 3:23 PM",
		"PACAF Sprint 11; 26/Jul/16 5:30 AM;  11/Aug/16 10:44 AM",
		"PACAF Sprint 12; 11/Aug/16 5:30 AM;  29/Aug/16 12:59 PM",
		"PACAF Sprint 13; 29/Aug/16 5:00 AM;  16/Sep/16 5:48 AM",
		"PACAF Sprint 14; 15/Sep/16 5:30 AM;  03/Oct/16 6:27 AM"
	};
	
	private static final String[] pacomSprintData = {
		"PACOM Sprint 1;  04/Feb/16 4:56 PM;  19/Feb/16 10:08 AM",
		"PACOM Sprint 2;  23/Feb/16 11:12 AM; 10/Mar/16 4:33 PM",
		"PACOM Sprint 3;  11/Mar/16 5:00 AM;  29/Mar/16 2:43 PM",
		"PACOM Sprint 4;  30/Mar/16 5:30 AM;  15/Apr/16 2:10 PM",
		"PACOM Sprint 5;  15/Apr/16 5:00 AM;  04/May/16 8:07 AM",
		"PACOM Sprint 6;  04/May/16 9:00 AM;  18/May/16 12:01 PM",
		"PACOM Sprint 7;  18/May/16 12:01 PM; 03/Jun/16 12:29 PM",
		"PACOM Sprint 8;  03/Jun/16 12:34 PM; 22/Jun/16 8:39 AM",
		"PACOM Sprint 9;  21/Jun/16 8:42 AM;  08/Jul/16 12:14 PM",
		"PACOM Sprint 10; 08/Jul/16 12:00 AM; 27/Jul/16 6:03 AM",
		"PACOM Sprint 11; 26/Jul/16 5:30 AM;  11/Aug/16 7:39 AM",
		"PACOM Sprint 12; 11/Aug/16 5:30 AM;  29/Aug/16 11:16 AM",
		"PACOM Sprint 13; 29/Aug/16 11:17 AM; 16/Sep/16 6:25 AM",
		"PACOM Sprint 14; 15/Sep/16 5:30 AM;  03/Oct/16 5:30 AM"
	};
	
	private static final String[] pacfltSprintData = {
		"PACFLT Sprint 1;  04/Feb/16 4:37 PM;  19/Feb/16 10:09 AM",
		"PACFLT Sprint 2;  23/Feb/16 11:09 AM; 10/Mar/16 4:31 PM",
		"PACFLT Sprint 3;  11/Mar/16 5:00 AM;  29/Mar/16 4:33 PM",
		"PACFLT Sprint 4;  30/Mar/16 8:11 AM;  15/Apr/16 2:16 PM",
		"PACFLT Sprint 5;  15/Apr/16 5:00 AM;  29/Apr/16 10:06 AM",
		"PACFLT Sprint 6;  29/Apr/16 12:00 AM; 18/May/16 10:39 AM",
		"PACFLT Sprint 7;  17/May/16 12:00 AM; 03/Jun/16 11:10 AM",
		"PACFLT Sprint 8;  03/Jun/16 12:00 AM; 21/Jun/16 1:17 PM",
		"PACFLT Sprint 9;  21/Jun/16 12:00 AM; 08/Jul/16 9:41 AM",
		"PACFLT Sprint 10; 08/Jul/16 12:00 AM; 28/Jul/16 10:02 AM",
		"PACFLT Sprint 11; 26/Jul/16 5:30 AM;  11/Aug/16 10:11 AM",
		"PACFLT Sprint 12; 11/Aug/16 12:00 AM; 29/Aug/16 12:46 PM",
		"PACFLT Sprint 13; 29/Aug/16 12:00 AM; 16/Sep/16 6:26 AM",
		"PACFLT Sprint 14; 15/Sep/16 12:00 AM; 03/Oct/16 12:00 AM"
	};
	
	private static final String[] letSprintData = {
		"LET Sprint 1;  04/Feb/16 4:57 PM;  19/Feb/16 10:09 AM",
		"LET Sprint 2;  23/Feb/16 11:11 AM; 10/Mar/16 4:32 PM",
		"LET Sprint 3;  11/Mar/16 5:01 AM;  30/Mar/16 8:17 AM",
		"LET Sprint 4;  30/Mar/16 8:25 AM;  15/Apr/16 2:16 PM",
		"LET Sprint 5;  15/Apr/16 5:00 AM;  04/May/16 1:42 PM",
		"LET Sprint 6;  05/May/16 12:33 PM; 18/May/16 10:44 AM",
		"LET Sprint 7;  18/May/16 1:18 PM;  06/Jun/16 3:30 PM",
		"LET Sprint 8;  06/Jun/16 3:40 PM;  22/Jun/16 8:44 AM",
		"LET Sprint 9;  21/Jun/16 8:44 AM;  08/Jul/16 10:56 AM",
		"LET Sprint 10; 08/Jul/16 6:00 AM;  27/Jul/16 12:52 PM",
		"LET Sprint 11; 26/Jul/16 1:00 PM;  11/Aug/16 7:42 AM",
		"LET Sprint 12; 11/Aug/16 5:30 AM;  29/Aug/16 4:21 PM",
		"LET Sprint 13; 29/Aug/16 5:00 AM;  16/Sep/16 6:26 AM"
	};
	
	private static final String[] miscSprintData = {
		"MISC Sprint 3;  11/Mar/16 5:00 AM; 15/Apr/16 2:16 PM",
		"MISC Sprint 5;  15/Apr/16 5:00 AM; 04/May/16 1:42 PM",
		"Misc Sprint 13; 29/Aug/16 5:30 PM; 16/Sep/16 12:16 PM",
		"Misc Sprint 14; 15/Sep/16 5:30 AM; 03/Oct/16 5:30 AM"		
	};
	
	
	public SprintFactory() {
		init();
	}
	
	public List<Sprint> getSprintsByNumber(int number) {
		List<Sprint> sprintList = new ArrayList<Sprint>();
		
		for (String key : nameToSprintMap.keySet()) {
			String[] splitKey = StringUtils.splitByWholeSeparator(key, "Sprint ");
			String stringNumber = splitKey[1];
			int keyNumber = Integer.parseInt(stringNumber);
			if (keyNumber == number) {
				sprintList.add(nameToSprintMap.get(key));
			}
		}
		
		return sprintList;
	}
	
	public Sprint getSprint(String name) {
		return nameToSprintMap.get(name);
	}
	
	private void init() {
		createSprints(pacafSprintData);
		createSprints(pacomSprintData);
		createSprints(pacfltSprintData);
		createSprints(letSprintData);
		createSprints(miscSprintData);
	}
	
	
	private void createSprints(String[] sprintDataArray) {
		if (nameToSprintMap == null) {
			nameToSprintMap = new HashMap<String, Sprint>();
		}
		for (String sprintData : sprintDataArray) {
			String[] data = sprintData.split(";");
			String name = data[0];
			Date startDate = DateUtil.getSprintDate(data[1].trim());
			Date endDate = DateUtil.getSprintDate(data[2].trim());
			nameToSprintMap.put(name, new Sprint(name, startDate, endDate));
		}
	}
	
}
