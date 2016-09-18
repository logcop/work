package com.cee.ljr.mapping;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cee.ljr.config.ApplicationConfig;
import com.cee.ljr.domain.common.WorkLog;
import com.cee.ljr.mapping.JiraIssueMapper;
import com.cee.ljr.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class JiraIssueMapperTest {
	
	private static final String DELIMITER = ";";
	private static final String DATE = "06/Apr/16 3:04 PM";
	private static final String OWNER = "logcop.enterprise";
	private static final String TIME = "3600";
	private static final Date date = DateUtil.toDate(DateUtil.JIRA_WORKLOG_DATE_FORMAT, DATE);
	private static final int time = new Integer(TIME);
	
	// ";06/Apr/16 3:04 PM;logcop.enterprise;3600"
	private static final String NO_COMMENT_WORKLOG_STR = 
			DELIMITER + DATE + DELIMITER + OWNER + DELIMITER + TIME;
	
	@Autowired
	private JiraIssueMapper jiraIssueMapper;
			
	
	@Test
	public void testCreateWorkLog() {
		WorkLog workLog = jiraIssueMapper.createWorkLog(NO_COMMENT_WORKLOG_STR);
		System.out.println("workLog string = " + NO_COMMENT_WORKLOG_STR);
		System.out.println(workLog.toString());
		assertTrue( StringUtils.isEmpty(workLog.getComment()));
		assertTrue( workLog.getDate() != null);
		assertTrue( workLog.getDate().equals(date) );
		assertTrue( StringUtils.isNotEmpty(workLog.getOwner()));
		assertTrue( workLog.getOwner().equals(OWNER));
		assertTrue( workLog.getTimeInSeconds() > 0);
		assertTrue( workLog.getTimeInSeconds() ==  time);
		
	}

}
