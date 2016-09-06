package com.cee.wsr.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

public class StatusReport {
	//private static final Logger log = LoggerFactory.getLogger(StatusReport.class);
	private String classification;
	
	private String title;
	private Sprint sprint;
	private Author author;
	private Date weekEndingDate;
	
	private Map<String, Project> keyToProjectMap = new HashMap<String, Project>(6,1.0f);

	public StatusReport(String title, String classification, Sprint sprint, Author author, Date weekEndingDate) {
		this.title = title;
		this.sprint = sprint;
		this.author = author;
		this.classification = classification;
		this.weekEndingDate = weekEndingDate;
	}
	
	public void addProject(Project project) {
		if(project == null) {
			throw new IllegalArgumentException("Project must not be null.");
		}
		
		String projectKey = project.getKey();
		if (keyToProjectMap.containsKey(projectKey)) {
			throw new RuntimeException("Project " + projectKey + " already exists in StatusReport.");
		}
		
		keyToProjectMap.put(projectKey, project);
		
		
	}
	
	public String logStats() {
		StringBuilder sb = new StringBuilder();
		sb.append("StatusReport.printStats...\n")
		.append("StatusReport Stats\n")
		.append("==================\n")
		.append("Title: ").append(title + "\n")
		.append("Sprint: ").append(sprint + "\n")
		.append("Author: ").append(author + "\n")
		.append("Classification: ").append(classification + "\n")
		.append("Weekending Date: ").append(weekEndingDate +"\n");
		
		for(Project project : keyToProjectMap.values()) {
			sb.append("Project Name: ").append(project.getName() + "\n")
			.append("Project Key: ").append(project.getKey() + "\n")
			.append("Project Total Hours Logged: ").append(project.getTotalLoggedHours() + "\n")
			.append("Time Spent: ").append(project.getTimeSpentInHours() + "\n");
			for (Epic epic : project.getEpics()) {
				sb.append("\tEpic Name:").append(epic.getName() + "\n")
				.append("\tEpic Key: ").append(epic.getKey() + "\n")
				.append("\tEpic Total Hours Logged: ").append(epic.getTotalLoggedHours() + "\n")
				.append("\tTime Spent: ").append(epic.getTimeSpentInHours() + "\n");
				for (Story story : epic.getStories()) {
					sb.append("\t\tStory Name: ").append(story.getName() + "\n")
					.append("\t\tStory Key: ").append(story.getKey() + "\n")
					.append("\t\tStory Total Hours Logged: ").append(story.getTotalLoggedHours() + "\n")
					.append("\t\tTime Spent: ").append(story.getTimeSpentInHours() + "\n");
					for(Task task : story.getTasks()) {
						sb.append("\t\t\tTask Summary: ").append(task.getSummary() + "\n")
						.append("\t\t\tTask Key: ").append(task.getKey() + "\n")
						.append("\t\t\tTask Story Points: ").append(task.getStoryPoints() + "\n")
						.append("\t\t\tTask Total Hours Logged: ").append(task.getTotalLoggedHours() + "\n")
						.append("\t\t\tTime Spent: ").append(task.getTimeSpentInHours() + "\n")
						.append("\t\t\tStatus: ").append(task.getStatus() + "\n");
						for(String developer : task.getDevelopers()) {
							sb.append("\t\t\tDeveloper: ").append(developer + "\n");
						}
					}
				}
			}
		}
		
		return sb.toString();
	}
	

	public Project getProject(String projectKey) {		
		return keyToProjectMap.get(projectKey);
	}	
	
	public Collection<Project> getProjects() {
		return keyToProjectMap.values();
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
