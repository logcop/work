package com.cee.ljr.intg.jira.domain;

public enum JiraAttribute {	
	ASSIGNEE("Assignee"),
	ATTACHMENT("Attachment"),
	COMMENT("Comment"),
	CREATED("Created"),
	CREATOR("Creator"),
	CUSTOM_FIELD_ACCOUNT("Custom field (Account)"),
	CUSTOM_FIELD_ASSIGNED_DEVELOPER("Custom field (Assigned Developer)"),
	CUSTOM_FIELD_DATE_OF_FIRST_RESPONSE("Custom field ([CHART] Date of First Response)"),
	CUSTOM_FIELD_DEVELOPMENT("Custom field (Development)"),
	CUSTOM_FIELD_EPIC_COLOR("Custom field (Epic Color)"),
	CUSTOM_FIELD_EPIC_LINK("Custom field (Epic Link)"),
	CUSTOM_FIELD_EPIC_NAME("Custom field (Epic Name)"),
	CUSTOM_FIELD_EPIC_STATUS("Custom field (Epic Status)"),
	CUSTOM_FIELD_ITERATION("Custom field (Iteration)"),
	CUSTOM_FIELD_ORGANIZATIONS("Custom field (Organizations)"),
	CUSTOM_FIELD_RAISED_DURING("Custom field (Raised during)"),
	CUSTOM_FIELD_RANK("Custom field (Rank)"),
	CUSTOM_FIELD_REPORTED_BY("Custom field (Reported by)"),
	CUSTOM_FIELD_STORY_POINTS("Custom field (Story Points)"),
	CUSTOM_FIELD_TEAM("Custom field (Team)"),
	CUSTOM_FIELD_TEST_SESSIONS("Custom field (Test sessions)"),
	CUSTOM_FIELD_TESTING_STATUS("Custom field (Testing status)"),
	CUSTOM_FIELD_PARENT_LINK("Custom field (Parent Link)"),
	DLTA_ORIG_ESTIMATE("Σ Original Estimate"),
	DLTA_REMAIN_ESTIMATE("Σ Remaining Estimate"),
	DLTA_TIME_SPENT("Σ Time Spent"),
	DESCRIPTION("Description"),
	DUE_DATE("Due Date"),
	ENVIRONMENT("Environment"),
	FIX_VERSIONS("Fix Version/s"),
	ISSUE_ID("Issue id"),
	ISSUE_KEY("Issue key"),
	ISSUE_TYPE("Issue Type"),
	LAST_VIEWED("Last Viewed"),
	LOG_WORK("Log Work"),
	ORIGINAL_ESTIMATE("Original Estimate"),
	OUTWARD_ISSUE_LINK_BLOCKS("Outward issue link (Blocks)"),
	OUTWARD_ISSUE_LINK_CLONERS("Outward issue link (Cloners)"),
	OUTWARD_ISSUE_LINK_DUPLICATE("Outward issue link (Duplicate)"),
	OUTWARD_ISSUE_LINK_PROBLEM_INCIDENT("Outward issue link (Problem/Incident)"),// Story tasks
	OUTWARD_ISSUE_LINK_RELATES("Outward issue link (Relates)"),
	PARENT_ID("Parent id"),//not found, maybe not used?
	PRIORITY("Priority"),
	PROJECT_DESCRIPTION("Project description"),
	PROJECT_LEAD("Project lead"),
	PROJECT_KEY("Project key"),
	PROJECT_NAME("Project name"),
	PROJECT_TYPE("Project type"),
	PROJECT_URL("Project url"),
	REMAINING_ESTIMATE("Remaining Estimate"),
	REPORTER("Reporter"),
	RESOLUTION("Resolution"),
	RESOLVED("Resolved"),
	SECURITY_LEVEL("Security Level"),
	SPRINT("Sprint"),//multi
	STATUS("Status"),
	SUMMARY("Summary"),
	TIME_SPENT("Time Spent"),
	UPDATED("Updated"),
	VOTES("Votes"),
	WATCHERS("Watchers"),
	WORK_RATIO("Work Ratio");
	
	private String value;
	
	private JiraAttribute(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
	public static JiraAttribute get(String value) {
		for (JiraAttribute attribute : JiraAttribute.values()) {
			if (attribute.value().equals(value)) {
				return attribute;
			}
		}
		return null;
	}

	
	/** 
	 * Returns the String "value" of this Enum.
	 **/
	@Override
	public String toString() {
		return value;
	}
	
	
}
