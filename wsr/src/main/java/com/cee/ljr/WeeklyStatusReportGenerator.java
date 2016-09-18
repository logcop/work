package com.cee.ljr;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.ljr.document.DocxGenerator;
import com.cee.ljr.domain.report.StatusReport;
import com.cee.ljr.service.StatusReportService;
import com.cee.ljr.spreadsheet.generator.XlsxWsrGenerator;
import com.cee.ljr.utils.DateUtil;

@Component
public class WeeklyStatusReportGenerator {
	private static final Logger log = LoggerFactory.getLogger(WeeklyStatusReportGenerator.class);
	
	@Autowired
	StatusReportService srService;
	@Autowired
	DocxGenerator docxGenerator;
	@Autowired
	XlsxWsrGenerator xlsxWsrGenerator;
	
	public void generateV1(String weekEndingDateStr) {
		Date weekStartDate = DateUtil.getWeekStartDate(weekEndingDateStr);
		Date weekEndingDate = DateUtil.getWeekEndingDate(weekEndingDateStr);
		StatusReport statusReport = srService.getWeeklyStatusReport(weekStartDate, weekEndingDate);
		docxGenerator.generateDocument(statusReport);
	}
	
	public void generateV2(String weekEndingDateStr){
		Date weekStartDate = DateUtil.getWeekStartDate(weekEndingDateStr);
		Date weekEndingDate = DateUtil.getWeekEndingDate(weekEndingDateStr);
		StatusReport statusReport = srService.getWeeklyStatusReport(weekStartDate, weekEndingDate);
		log.debug("status report initialized..");
		xlsxWsrGenerator.generateWsrDocument(statusReport);
		log.debug("status report generated..");
	}
}
