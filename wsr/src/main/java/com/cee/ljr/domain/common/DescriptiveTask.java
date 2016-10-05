package com.cee.ljr.domain.common;

public class DescriptiveTask extends Task {

	private Epic epic;
	
	public DescriptiveTask() {
		super();
	}
	
	/**
	 * @return the epic
	 */
	public Epic getEpic() {
		return epic;
	}


	/**
	 * @param epic the epic to set
	 */
	public void setEpic(Epic epic) {
		this.epic = epic;
	}


	public DescriptiveTask(String summary) {
		super(summary);
		// TODO Auto-generated constructor stub
	}

	
	
}
