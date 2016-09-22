package com.cee.ljr.domain.common;

public class DescriptiveTask extends Task {

	private String epicName;
	private String storySummary;
	
	
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



	/**
	 * @return the storySummary
	 */
	public String getStorySummary() {
		return storySummary;
	}



	/**
	 * @param storySummary the storySummary to set
	 */
	public void setStorySummary(String storySummary) {
		this.storySummary = storySummary;
	}



	public DescriptiveTask(String summary) {
		super(summary);
		// TODO Auto-generated constructor stub
	}

	
	
}
