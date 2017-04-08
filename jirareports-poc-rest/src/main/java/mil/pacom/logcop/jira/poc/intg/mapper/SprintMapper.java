package mil.pacom.logcop.jira.poc.intg.mapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

import mil.pacom.logcop.jira.poc.domain.Sprint;

public abstract class SprintMapper {

	public static Sprint mapSprintObject(Object sprintObj) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String str = sprintObj.toString();
		String keyValue = str.substring(str.indexOf("[") + 1, str.lastIndexOf("]"));
		StringTokenizer tok = new StringTokenizer(keyValue, ",");
		Map<String, String> map = new LinkedHashMap<String, String>();
		
		while (tok.hasMoreTokens()) {
		    String entString = tok.nextToken();
		    String[] propertyValuePair = entString.split("=");
		    String property = propertyValuePair[0];
		    String value = (propertyValuePair.length == 2) ? propertyValuePair[1] : ""; 
		    map.put(property, value);
		}
		
		Sprint sprint = null;
		
		try {		
			sprint = 
				new Sprint(map.get("id"), 
						map.get("rapidViewId"),
						map.get("state"),
						map.get("name"), 
						map.get("goal"), 
						df.parse(map.get("startDate")), 
						df.parse(map.get("endDate")), 
						df.parse(map.get("completeDate")), 
						map.get("sequence"));
		} catch(ParseException pe) {
			System.out.println("Unable to parse date field...");
			pe.printStackTrace();
		}
		return sprint;
	}

}
