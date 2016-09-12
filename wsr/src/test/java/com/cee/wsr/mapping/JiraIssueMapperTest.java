package com.cee.wsr.mapping;

import java.util.Date;

import junit.framework.TestCase;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import com.cee.wsr.domain.common.WorkLog;
import com.cee.wsr.utils.DateUtil;

public class JiraIssueMapperTest extends TestCase {
	
	private static final String DELIMITER = ";";
	private static final String DATE = "06/Apr/16 3:04 PM";
	private static final String OWNER = "logcop.enterprise";
	private static final String TIME = "3600";
	private static final Date date = DateUtil.toDate(DateUtil.JIRA_WORKLOG_DATE_FORMAT, DATE);
	private static final int time = new Integer(TIME);
	
	// ";06/Apr/16 3:04 PM;logcop.enterprise;3600"
	private static final String NO_COMMENT_WORKLOG_STR = 
			DELIMITER + DATE + DELIMITER + OWNER + DELIMITER + TIME;
			
	
	public void testCreateWorkLog() {
		WorkLog workLog = JiraIssueMapper.createWorkLog(NO_COMMENT_WORKLOG_STR);
		System.out.println("workLog string = " + NO_COMMENT_WORKLOG_STR);
		System.out.println(workLog.toString());
		Assert.assertTrue( StringUtils.isEmpty(workLog.getComment()));
		Assert.assertTrue( workLog.getDate() != null);
		Assert.assertTrue( workLog.getDate().equals(date) );
		Assert.assertTrue( StringUtils.isNotEmpty(workLog.getOwner()));
		Assert.assertTrue( workLog.getOwner().equals(OWNER));
		Assert.assertTrue( workLog.getTimeInSeconds() > 0);
		Assert.assertTrue( workLog.getTimeInSeconds() ==  time);
		
	}

}
