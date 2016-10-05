package com.cee.ljr.document.generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.domain.common.Task;
import com.cee.ljr.domain.report.DeveloperSprintReport;
import com.cee.ljr.properties.ReportProperties;
import com.cee.ljr.utils.FileUtil;

@Component
public class DeveloperSprintReportXlsxGenerator {
	private static final Logger log = LoggerFactory.getLogger(DeveloperSprintReportXlsxGenerator.class);
	
	@Autowired
	ReportProperties props;
	//DeveloperSprint
	private static final int devNameColIndex = 0;
	private static final int taskKeyColIndex = 1;
	private static final int summaryColIndex = 2;
	private static final int descriptionColIndex = 3;
	private static final int sprintColIndex = 4;
	private static final int storyPointsColIndex = 5;
	private static final int timeSpentColIndex = 6;
	private static final int statusColIndex = 7;
	private static final int epicNameColIndex = 8;
	
	
	public void generate(DeveloperSprintReport developerSprintReport, String reportPath) {
		if (developerSprintReport == null) {
			throw new IllegalArgumentException("developerSprintReport must not be null.");
		}
		
		generateSpreadsheet(developerSprintReport, reportPath);
	}
	
	private void generateSpreadsheet(DeveloperSprintReport dsr, String reportPath) {
		log.info("Developer Sprint Report generating.");
		Workbook wb = new XSSFWorkbook();
		Developer developer = dsr.getDeveloper();
		List<Task> tasks = dsr.getTasks();
		int sprintNumber = dsr.getSprintNumber();
		Date sprintStartDate = dsr.getSprintStartDate();
		Date sprintEndDate = dsr.getSprintEndDate();
		
		Sheet sheet = wb.createSheet(developer.getNameInJira() + " - Sprint " + sprintNumber);
		MutableInt nextRowIndex = new MutableInt(0);
		
		createHeader(sheet, nextRowIndex);
		
		for (Task task : tasks) {
			addTask(sheet, nextRowIndex, task, sprintStartDate, sprintEndDate);
		}
		
		
		String filePath = buildFilePath(reportPath, developer.getFullName(), sprintNumber);
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(filePath);
	        wb.write(fileOut);
	        wb.close();
	        fileOut.close();
	        log.info("Developer Sprint Report generated.");
		}
		catch (IOException ioe) {
			log.error("Unable to create new workbook.", ioe);
		}
	}
	
	private void addTask(Sheet sheet, MutableInt nextRowIndex, Task task, Date startDate, Date endDate) {
		Row row = sheet.createRow(nextRowIndex.intValue());
		Cell cell = row.createCell(devNameColIndex);
		//cell.setCellValue(task.getDevelopers());
		cell = row.createCell(taskKeyColIndex);
		cell.setCellValue(task.getKey());
		cell = row.createCell(summaryColIndex);
		cell.setCellValue(task.getSummary());
		cell = row.createCell(descriptionColIndex);
		cell.setCellValue(task.getDescription());
		cell = row.createCell(sprintColIndex);
		//cell.setCellValue(task.getSprints());
		cell = row.createCell(storyPointsColIndex);
		cell.setCellValue(task.getStoryPoints());
		cell = row.createCell(timeSpentColIndex);
		cell.setCellValue(task.getHoursWorkedBetween(startDate, endDate));
		cell = row.createCell(statusColIndex);
		cell.setCellValue(task.getStatus());
		cell = row.createCell(epicNameColIndex);
		cell.setCellValue(task.getSummary());
		
		nextRowIndex.add(1);
	}
	
	private String buildFilePath(String reportPath, String developerName, int sprintNumber) {
		if (reportPath == null) {
			reportPath = props.getReportPath();
		}
		if (!FileUtil.isValidPath(reportPath)) {
			log.error("report path [{}] is invalid!", reportPath);
			return null;
		}
		String documentName = generateReportName(developerName, sprintNumber);
		
		return Paths.get(reportPath, documentName).toAbsolutePath().toString();
	}
	
	private String generateReportName(String developerName, int sprintNumber) {
		return new StringBuilder()
		.append(developerName)
		.append(" Sprint # ")
		.append(sprintNumber)
		.append(" Hours Report.xlsx")
		.toString();
	}
	
	private void createHeader(Sheet sheet, MutableInt nextRowIndex) {
		Row row = sheet.createRow(nextRowIndex.intValue());
		Cell cell = row.createCell(devNameColIndex);
		cell.setCellValue("Developer");
		cell = row.createCell(taskKeyColIndex);
		cell.setCellValue("Key");
		cell = row.createCell(summaryColIndex);
		cell.setCellValue("Summary");
		cell = row.createCell(descriptionColIndex);
		cell.setCellValue("Description");
		cell = row.createCell(sprintColIndex);
		cell.setCellValue("Sprint");
		cell = row.createCell(storyPointsColIndex);
		cell.setCellValue("Story Points");
		cell = row.createCell(timeSpentColIndex);
		cell.setCellValue("Sprint Time Spent");
		cell = row.createCell(statusColIndex);
		cell.setCellValue("Status");
		cell = row.createCell(epicNameColIndex);
		cell.setCellValue("Epic");
		
		nextRowIndex.add(1);
	}
}
