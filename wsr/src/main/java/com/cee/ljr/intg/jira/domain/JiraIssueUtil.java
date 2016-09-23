package com.cee.ljr.intg.jira.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public abstract class JiraIssueUtil {

	public static final List<String> getEpicKeys(List<JiraIssue> jiraIssues) {
		List<String> epicKeys = new ArrayList<String>();
		
		for (JiraIssue jiraIssue : jiraIssues) {
			String epicKey = jiraIssue.getEpicKey();
			if (StringUtils.isNotBlank(epicKey)) {
				epicKeys.add(epicKey);
			}
		}
		
		return epicKeys;
	}
}
