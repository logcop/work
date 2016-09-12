package com.cee.wsr;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.wsr.document.DocxGenerator;
import com.cee.wsr.domain.report.StatusReport;
import com.cee.wsr.service.StatusReportService;
import com.cee.wsr.spreadsheet.generator.XlsxWsrGenerator;
import com.cee.wsr.utils.DateUtil;

@Component
public class StatusReportGenerator {
	private static final Logger log = LoggerFactory.getLogger(StatusReportGenerator.class);
	
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
