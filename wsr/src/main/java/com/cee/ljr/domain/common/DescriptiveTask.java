package com.cee.ljr.domain.common;

public class DescriptiveTask extends Task {

	private String epicName;
	
	public DescriptiveTask() {
		super();
	}
	
	/**
	 * @return the epicName
	 */
	public String getEpicName() {
		return epicName;
	}


	/**
	 * @param epicName the epicName to set
	 */
	public void setEpicName(String epicName) {
		this.epicName = epicName;
	}


	public DescriptiveTask(String summary) {
		super(summary);
		// TODO Auto-generated constructor stub
	}

	
	
}
