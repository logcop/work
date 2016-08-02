package com.cee.wsr.properties;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cee.wsr.utils.DateUtil;

@Component
public class StatusReportProperties {

	private String reportTitle;
	private Date reportWeekEndingDate;
	private String reportClassification;
	private int sprintNumber;
	private Date sprintStartDate;
	private Date sprintEndDate;
	private String authorName;
	private String authorTitle;
	private String authorType;
	private String xlsPath;
	private String docxPath;

	@Autowired
	public StatusReportProperties(
			@Value("${report.title}") String reportTitle,
			@Value("${report.weekendingdate}") String reportWeekEndingDate,
			@Value("${report.classification}") String reportClassification,
			@Value("${sprint.number}") String sprintNumber,
			@Value("${sprint.startdate}") String sprintStartDate,
			@Value("${sprint.enddate}") String sprintEndDate,
			@Value("${author.name}") String authorName,
			@Value("${author.title}") String authorTitle,
			@Value("${author.type}") String authorType, 
			@Value("${xls.path}") String xlsPath,
			@Value("${docx.path}") String docxPath) {
		// Validation can go here if needed, throwing IllegalArgumentException
		// if it fails..
		this.reportTitle = reportTitle;
		this.reportWeekEndingDate = DateUtil.toDate(reportWeekEndingDate)
;		this.reportClassification = reportClassification;
		this.sprintNumber = Integer.parseInt(sprintNumber);
		this.sprintStartDate = DateUtil.toDate(sprintStartDate);
		this.sprintEndDate = DateUtil.toDate(sprintEndDate);
		this.authorName = authorName;
		this.authorTitle = authorTitle;
		this.authorType = authorType;
		this.xlsPath = xlsPath;
		this.docxPath = docxPath;
	}

	/**
	 * @return the reportTitle
	 */
	public String getReportTitle() {
		return reportTitle;
	}

	
	
	/**
	 * @return the reportWeekEndingDate
	 */
	public Date getReportWeekEndingDate() {
		return reportWeekEndingDate;
	}

	/**
	 * @return the reportClassification
	 */
	public String getReportClassification() {
		return reportClassification;
	}

	/**
	 * @return the sprintNumber
	 */
	public int getSprintNumber() {
		return sprintNumber;
	}

	/**
	 * @return the sprintStartDate
	 */
	public Date getSprintStartDate() {
		return sprintStartDate;
	}

	/**
	 * @return the sprintEndDate
	 */
	public Date getSprintEndDate() {
		return sprintEndDate;
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
	 * @return the xlsPath
	 */
	public String getXlsPath() {
		return xlsPath;
	}
	
	public void setXlsPath(String xlsxPath) {
		this.xlsPath = xlsxPath;
	}

	/**
	 * @return the docxPath
	 */
	public String getDocxPath() {
		return docxPath;
	}

}
