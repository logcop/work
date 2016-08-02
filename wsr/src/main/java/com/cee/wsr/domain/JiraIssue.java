package com.cee.wsr.domain;

import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class JiraIssue {
	private String projectName;
	private String key;
	private String summary;
	private String type;
	private String status;
	private String epic;
	private float estimate;
	private float timeSpent;
	private String[] subTasks;
	private String[] linkedIssueKeys;
	private Set<String> developers;

	public JiraIssue(String projectName, String epic, 
			String summary, String status, Set<String> developers) {
		this.projectName = projectName;
		this.epic = epic;
		this.summary = summary;
		this.status = status;
		this.developers = developers;
	}	
	

	public JiraIssue() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}


	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	/**
	 * @return the subTasks
	 */
	public String[] getSubTasks() {
		return subTasks;
	}


	/**
	 * @param subTasks the subTasks to set
	 */
	public void setSubTasks(String[] subTasks) {
		this.subTasks = subTasks;
	}


	/**
	 * @return the epic
	 */
	public String getEpic() {
		return epic;
	}


	/**
	 * @param epic the epic to set
	 */
	public void setEpic(String epic) {
		this.epic = epic;
	}


	/**
	 * @param developers the developers to set
	 */
	public void setDevelopers(Set<String> developers) {
		this.developers = developers;
	}



	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the developers
	 */
	public Set<String> getDevelopers() {
		return developers;
	}
	
	

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}


	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the estimate
	 */
	public float getEstimate() {
		return estimate;
	}


	/**
	 * @param estimate the estimate to set
	 */
	public void setEstimate(float estimate) {
		this.estimate = estimate;
	}


	/**
	 * @return the timeSpent
	 */
	public float getTimeSpent() {
		return timeSpent;
	}


	/**
	 * @param timeSpent the timeSpent to set
	 */
	public void setTimeSpent(float timeSpent) {
		this.timeSpent = timeSpent;
	}


	/**
	 * @return the linkedIssueKeys
	 */
	public String[] getLinkedIssueKeys() {
		return linkedIssueKeys;
	}


	/**
	 * @param linkedIssueKeys the linkedIssueKeys to set
	 */
	public void setLinkedIssueKeys(String[] linkedIssueKeys) {
		this.linkedIssueKeys = linkedIssueKeys;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
