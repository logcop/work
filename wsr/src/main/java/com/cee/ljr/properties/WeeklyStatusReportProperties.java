package com.cee.ljr.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeeklyStatusReportProperties {

	private String reportTitle;
	private String reportClassification;
	private String authorName;
	private String authorTitle;
	private String authorType;
	private String docxPath;

	@Autowired
	public WeeklyStatusReportProperties(
			@Value("${report.title}") String reportTitle,
			@Value("${report.classification}") String reportClassification,
			@Value("${author.name}") String authorName,
			@Value("${author.title}") String authorTitle,
			@Value("${author.type}") String authorType,
			@Value("${docx.path}") String docxPath) {
		// Validation can go here if needed, throwing IllegalArgumentException
		// if it fails..
		this.reportTitle = reportTitle;
;		this.reportClassification = reportClassification;
		this.authorName = authorName;
		this.authorTitle = authorTitle;
		this.authorType = authorType;
		this.docxPath = docxPath;
	}

	/**
	 * @return the reportTitle
	 */
	public String getReportTitle() {
		return reportTitle;
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
	 * @return the docxPath
	 */
	public String getDocxPath() {
		return docxPath;
	}

}
