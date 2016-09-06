package com.cee.wsr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.wsr.document.DocxGenerator;
import com.cee.wsr.domain.StatusReport;
import com.cee.wsr.service.StatusReportService;
import com.cee.wsr.spreadsheet.generator.XlsxWsrGenerator;

@Component
public class StatusReportGenerator {
	private static final Logger log = LoggerFactory.getLogger(StatusReportGenerator.class);
	
	@Autowired
	StatusReportService srService;
	@Autowired
	DocxGenerator docxGenerator;
	@Autowired
	XlsxWsrGenerator xlsxWsrGenerator;
	
	public void generateV1() {
		StatusReport statusReport = srService.getStatusReport();
		docxGenerator.generateDocument(statusReport);
	}
	
	public void generateV2(){
		StatusReport statusReport = srService.getStatusReport();
		log.debug("status report initialized..");
		xlsxWsrGenerator.generateDocument(statusReport);
		log.debug("status report generated..");
	}
}
