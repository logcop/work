package com.cee.wsr.spreadsheet.generator;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cee.wsr.domain.Epic;
import com.cee.wsr.domain.Project;
import com.cee.wsr.domain.StatusReport;
import com.cee.wsr.domain.Story;
import com.cee.wsr.domain.Task;

@Component
public class XlsxWsrGenerator {
	private static final Logger log = LoggerFactory.getLogger(XlsxWsrGenerator.class);
	
	private static final String WsrPath = System.getProperty("user.dir") + "/report.xlsx";
	private static final int epicColIndex = 0;
	private static final int storyColIndex = 1;
	private static final int taskColIndex = 2;
	private static final int runningTotalColIndex = 3;
	
	public void generateDocument(StatusReport statusReport) {
		if (statusReport == null) {
			throw new IllegalArgumentException("statusReport must not be null.");
		}
		if (CollectionUtils.isEmpty(statusReport.getProjects())) {
			log.error("statusReport must contain at least one project");
			return;
		}
		
		Workbook wb = new XSSFWorkbook();
		
		for (Project project : statusReport.getProjects()) {
			addProject(project, wb);
		}
		
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(WsrPath);
	        wb.write(fileOut);
	        fileOut.close();
		}
		catch (IOException ioe) {
			log.error("Unable to create new workbook.", ioe);
		}
	}
	
	private void addProject(Project project, Workbook wb) {
		Sheet sheet = wb.createSheet(project.getName());
		MutableInt nextRowIndex = new MutableInt(-1);
		for (Epic epic : project.getEpics()) {
			nextRowIndex.add(1);
			addEpic(epic, sheet, nextRowIndex);
		}
	}
	
	private void addEpic(Epic epic, Sheet sheet, MutableInt nextRowIndex) {
		Row row = sheet.createRow(nextRowIndex.intValue());
		Cell cell = row.createCell(epicColIndex);
		cell.setCellValue(epic.getName());
		cell = row.createCell(runningTotalColIndex);
		cell.setCellValue("Running Total: " + epic.getTotalLoggedHours());
		for (Story story : epic.getStories()) {
			nextRowIndex.add(1);
			addStory(story, sheet, nextRowIndex);
		}
	}
	
	private void addStory(Story story, Sheet sheet, MutableInt nextRowIndex) {
		Row row = sheet.createRow(nextRowIndex.intValue());
		Cell cell = row.createCell(storyColIndex);
		cell.setCellValue("       " + story.getName());
		cell = row.createCell(runningTotalColIndex);
		cell.setCellValue("Running Total: " + story.getTotalLoggedHours());
		for (Task task : story.getTasks()) {
			nextRowIndex.add(1);
			addTask(task, sheet, nextRowIndex);
		}
	}
	
	private void addTask(Task task, Sheet sheet, MutableInt nextRowIndex) {
		Row row = sheet.createRow(nextRowIndex.intValue());
		Cell cell = row.createCell(taskColIndex);
		cell.setCellValue(task.getSummary());
		cell = row.createCell(runningTotalColIndex);
		cell.setCellValue("Running Total: " + task.getTotalLoggedHours());
	}
	
}
