package com.cee.wsr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.wsr.document.DocxGenerator;
import com.cee.wsr.domain.StatusReport;
import com.cee.wsr.service.StatusReportService;
import com.cee.wsr.spreadsheet.XlsxGenerator;

@Component
public class StatusReportGenerator {
	@Autowired
	StatusReportService srService;
	@Autowired
	DocxGenerator docxGenerator;
	@Autowired
	XlsxGenerator xlsxGenerator;
	
	public void generate() {
		StatusReport statusReport = srService.getStatusReport();
		docxGenerator.generateDocument(statusReport);
	}
	
	public void generateV2(){
		StatusReport statusReport = srService.getStatusReport();
		xlsxGenerator.generateDocument(statusReport);
	}
}
