package com.cee.ljr.domain.common;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class BaseIssue {
	//private static final Logger log = LoggerFactory.getLogger(BaseIssue.class);
	
	protected String summary;
	protected String key;
	protected String id;
	protected String issueType;
	protected String status;
	protected String priority;
	protected String created;
	protected String updated;
	protected String description;
	protected Set<Sprint> sprints;
	
	
	
	
	/**
	 * <b>WARNING: If this method is called and the results are greater than zero, it will not update again.
	 * Assumes this issue is completely initialized.</b>
	 * This is to speed up document generation at the end. <br><br>
	 * Gets the total hours worked between the given dates. 
	 * @param startDate The start Date of the range to get the hours for.
	 * @param endDate The end Date of the range to get the hours for.
	 * @return The hours worked.
	 */
	public abstract float getHoursWorkedBetween(Date startDate, Date endDate);
	
	/**
	 * <b>WARNING: If this method is called and the results are greater than zero, it will not update again.
	 * Assumes this issue is completely initialized.</b>
	 * This is to speed up document generation at the end. <br><br>
	 * Gets the running total hours worked. 
	 * @param startDate The start Date of the range to get the hours for.
	 * @param endDate The end Date of the range to get the hours for.
	 * @return The running total of the hours worked.
	 */
	public abstract float getTotalHoursWorked();
	
	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the issueType
	 */
	public String getIssueType() {
		return issueType;
	}
	/**
	 * @param issueType the issueType to set
	 */
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}
	/**
	 * @return the updated
	 */
	public String getUpdated() {
		return updated;
	}
	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the sprints
	 */
	public Set<Sprint> getSprints() {
		return sprints;
	}
	/**
	 * @param sprints the sprints to set
	 */
	public void setSprints(Set<Sprint> sprints) {
		this.sprints = sprints;
	}
	
	public String toStringLight() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
		sb.append("issueType", this.issueType);
		sb.append("key", this.key);
		sb.append("summary", this.summary);
		return sb.toString();
	}
	
}
