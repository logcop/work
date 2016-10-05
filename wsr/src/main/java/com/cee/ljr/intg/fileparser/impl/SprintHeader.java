package com.cee.ljr.intg.fileparser.impl;

public enum SprintHeader {
	SPRINT_NAME("Sprint Name"),
	START_DATE("Start Date"),
	END_DATE("End Date");
	
	private String value;
	
	private SprintHeader(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
