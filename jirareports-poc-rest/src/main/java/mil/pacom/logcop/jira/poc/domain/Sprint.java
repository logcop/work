package mil.pacom.logcop.jira.poc.domain;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Sprint {
	
	private String id;
	private String rapidViewId;
	private String state;
	private String name;
	private String goal;
	private Date startDate;
	private Date endDate;
	private Date completeDate;
	private String sequence;
	
	public Sprint(String id, String rapidViewId, String state, String name,
			String goal, Date startDate, Date endDate, Date completeDate,
			String sequence) {
		super();
		this.id = id;
		this.rapidViewId = rapidViewId;
		this.state = state;
		this.name = name;
		this.goal = goal;
		this.startDate = startDate;
		this.endDate = endDate;
		this.completeDate = completeDate;
		this.sequence = sequence;
	}
	

	public String getId() {
		return id;
	}

	public String getRapidViewId() {
		return rapidViewId;
	}

	public String getState() {
		return state;
	}

	public String getName() {
		return name;
	}

	public String getGoal() {
		return goal;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public String getSequence() {
		return sequence;
	}
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
