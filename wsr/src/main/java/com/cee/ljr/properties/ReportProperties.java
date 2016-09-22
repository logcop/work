package com.cee.ljr.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReportProperties {

	private String wsrReportTitle;
	private String reportClassification;
	private String authorName;
	private String authorTitle;
	private String authorType;
	private String reportPath;

	@Autowired
	public ReportProperties(
			@Value("${wsr.report.title}") String wsrReportTitle,
			@Value("${report.classification}") String reportClassification,
			@Value("${author.name}") String authorName,
			@Value("${author.title}") String authorTitle,
			@Value("${author.type}") String authorType,
			@Value("${default.report.path}") String reportPath) {
		// Validation can go here if needed, throwing IllegalArgumentException
		// if it fails..
		this.wsrReportTitle = wsrReportTitle;
;		this.reportClassification = reportClassification;
		this.authorName = authorName;
		this.authorTitle = authorTitle;
		this.authorType = authorType;
		this.reportPath = reportPath;
	}

	/**
	 * @return the reportTitle
	 */
	public String getWeeklyStatusReportTitle() {
		return wsrReportTitle;
	}

	/**
	 * @return the reportClassification
	 */
	public String getReportClassification() {
		return reportClassification;
	}
	

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @return the authorTitle
	 */
	public String getAuthorTitle() {
		return authorTitle;
	}

	/**
	 * @return the authorType
	 */
	public String getAuthorType() {
		return authorType;
	}
	

	/**
	 * @return the reportPath
	 */
	public String getReportPath() {
		return reportPath;
	}

}
