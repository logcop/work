package com.cee.ljr.domain.report;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.domain.common.Task;

public class DeveloperSprintReport {
	
	private int sprintNumber;
	private Date sprintStartDate;
	private Date sprintEndDate;
	
	private Developer developer;
	private List<Task> tasks;
	
	
	
	public DeveloperSprintReport(int sprintNumber, Date sprintStartDate, Date sprintEndDate, Developer developer,
			List<Task> tasks) {
		super();
		this.sprintNumber = sprintNumber;
		this.sprintStartDate = sprintStartDate;
		this.sprintEndDate = sprintEndDate;
		this.developer = developer;
		this.tasks = tasks;
	}
	

	public int getSprintNumber() {
		return sprintNumber;
	}



	public Date getSprintStartDate() {
		return sprintStartDate;
	}



	public Date getSprintEndDate() {
		return sprintEndDate;
	}



	public Developer getDeveloper() {
		return developer;
	}



	public List<Task> getTasks() {
		return tasks;
	}
	
	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
		sb.append("sprintNumber", sprintNumber);
		sb.append("sprintStartDate", sprintStartDate);
		sb.append("sprintEndDate", sprintEndDate);
		sb.append("developer", developer);
		sb.append("tasks", tasks);
		return sb.toString();
	}
	

}
