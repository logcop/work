package com.cee.ljr;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.cee.ljr.document.generator.WeeklyStatusReportDocxGenerator;
import com.cee.ljr.document.generator.WeeklyStatusReportXlsxGenerator;
import com.cee.ljr.domain.report.WeeklyStatusReport;
import com.cee.ljr.service.WeeklyStatusReportService;
import com.cee.ljr.utils.DateUtil;

@Component
@PropertySource("classpath:/properties/weekly-status-report.properties")
public class WeeklyStatusReportGenerator {
	private static final Logger log = LoggerFactory.getLogger(WeeklyStatusReportGenerator.class);
	
	@Autowired
	private WeeklyStatusReportService srService;
	@Autowired
	private WeeklyStatusReportDocxGenerator weeklyStatusReportDocxGenerator;
	@Autowired
	private WeeklyStatusReportXlsxGenerator weeklyStatusReportXlsxGenerator;
	
	@Value("${report.title}")
	private String reportTitle;
	
	public void generateV1(String weekEndingDateStr) {
		Date weekStartDate = DateUtil.getWeekStartDate(weekEndingDateStr);
		Date weekEndingDate = DateUtil.getWeekEndingDate(weekEndingDateStr);
		WeeklyStatusReport weeklyStatusReport = srService.getWeeklyStatusReport(weekStartDate, weekEndingDate);
		weeklyStatusReportDocxGenerator.generateDocument(weeklyStatusReport);
	}
	
	public void generateV2(String weekEndingDateStr){
		Date weekStartDate = DateUtil.getWeekStartDate(weekEndingDateStr);
		Date weekEndingDate = DateUtil.getWeekEndingDate(weekEndingDateStr);
		log.info("{} initializing.", reportTitle);
		WeeklyStatusReport weeklyStatusReport = srService.getWeeklyStatusReport(weekStartDate, weekEndingDate);
		log.info(" {} initialized.", reportTitle);
		log.info("{} generating.", reportTitle);
		weeklyStatusReportXlsxGenerator.generateWsrDocument(weeklyStatusReport);
		log.info("{} generated.", reportTitle);
	}
}
