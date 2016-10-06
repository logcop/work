package com.cee.ljr.intg.jira.fileparser;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.file.csv.criteria.Criteria;
import com.cee.ljr.intg.fileparser.impl.BaseCsvFileParser;
import com.cee.ljr.intg.jira.domain.JiraIssue;
import com.cee.ljr.intg.mapping.JiraIssueMapper;

/**
 * this is gonna be the new one... yessiree..
 * @author chuck
 *
 */
@Component
public class JiraIssuesCsvFileParser2 extends BaseCsvFileParser<JiraIssue>{
	private static final Logger log = LoggerFactory.getLogger(JiraIssuesCsvFileParser2.class);
	
	private static final boolean WITH_HEADER = true;
	
	@Autowired
	JiraIssueMapper mapper;
	
	public JiraIssuesCsvFileParser2() {
		super(WITH_HEADER);
	}

	@Override
	protected List<JiraIssue> parseForAll(String filePaths, Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<JiraIssue> parseForAll(String filePaths) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JiraIssue parseForSingle(String filePaths, Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
