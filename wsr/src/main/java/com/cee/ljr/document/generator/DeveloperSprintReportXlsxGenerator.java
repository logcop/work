package com.cee.ljr.document.generator;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cee.ljr.domain.report.DeveloperSprintReport;

public class DeveloperSprintReportXlsxGenerator {
	private static final Logger log = LoggerFactory.getLogger(DeveloperSprintReportXlsxGenerator.class);
	
	public void generate(DeveloperSprintReport developerSprintReport) {
		if (developerSprintReport == null) {
			throw new IllegalArgumentException("developerSprintReport must not be null.");
		}
		
		generateSpreadsheet(developerSprintReport);
	}
	
	private void generateSpreadsheet(DeveloperSprintReport developerSprintReport) {
		Workbook wb = new XSSFWorkbook();
				String wsrPath = ""; // TODO: STOPPED HERE......
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(wsrPath);
	        wb.write(fileOut);
	        fileOut.close();
		}
		catch (IOException ioe) {
			log.error("Unable to create new workbook.", ioe);
		}
	}
}
