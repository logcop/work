package com.cee.wsr.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class StatusReport {
	private String classification;
	
	private String title;
	private Sprint sprint;
	private Author author;
	private Date weekEndingDate;
	
	private List<Project> projectList = new ArrayList<Project>();

	// private List<Appendix> appendixList;

	public StatusReport(String title, String classification, Sprint sprint, Author author, Date weekEndingDate) {
		this.title = title;
		this.sprint = sprint;
		this.author = author;
		this.classification = classification;
		this.weekEndingDate = weekEndingDate;
	}

	public Project getProject(String name) {
		for (Project project : projectList) {
			if (project.getName().equals(name)) {
				return project;
			}
		}
		return null;
	}
	
	

	/**
	 * TODO: rewrite....
	 * Adds a task to the given named project. If the project doesn't exist, a
	 * new project is created, the task is added to it, and it is associated
	 * with the StatusReport.
	 * 
	 * @param projectName
	 * @param epic
	 * @param jiraIssue
	 */
	public void addJiraIssue(JiraIssue jiraIssue) {
		if (jiraIssue == null) {
			throw new IllegalArgumentException("JiraIssue cannot be null.");
		}

		Project project = getProject(jiraIssue.getProjectName());
		if (project == null) {
			project = new Project(jiraIssue.getProjectName());
			project.addJiraIssue(jiraIssue);
			projectList.add(project);
		} else {
			project.addJiraIssue(jiraIssue);
		}
	}
	
	public void addJiraIssues(List<JiraIssue> jiraIssues) {
		for (JiraIssue jiraIssue : jiraIssues) {
			addJiraIssue(jiraIssue);
		}
	}

	public void addProject(Project project) {
		if (!this.containsProject(project)) {
			projectList.add(project);
		}
	}

	public boolean containsProject(Project project) {
		return containsProject(project.getName());
	}

	public boolean containsProject(String projectName) {
		for (Project existingProject : projectList) {
			if (existingProject.getName().equals(projectName)) {
				return true;
			}
		}
		return false;
	}
	
	public List<Project> getProjects() {
		return projectList;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the sprint
	 */
	public Sprint getSprint() {
		return sprint;
	}

	/**
	 * @param sprint
	 *            the sprint to set
	 */
	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	/**
	 * @return the author
	 */
	public Author getPreparer() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setPreparer(Author author) {
		this.author = author;
	}
	
	/**
	 * @return the weekEndingDate
	 */
	public Date getWeekEndingDate() {
		return weekEndingDate;
	}

	/**
	 * @param weekEndingDate the weekEndingDate to set
	 */
	public void setWeekEndingDate(Date weekEndingDate) {
		this.weekEndingDate = weekEndingDate;
	}

	/**
	 * @return the classification
	 */
	public String getClassification() {
		return classification;
	}

	/**
	 * @param classification
	 *            the classification to set
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
