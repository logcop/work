package com.cee.ljr.document.generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cee.ljr.domain.common.Epic;
import com.cee.ljr.domain.common.Project;
import com.cee.ljr.domain.common.Story;
import com.cee.ljr.domain.common.Task;
import com.cee.ljr.domain.report.WeeklyStatusReport;
import com.cee.ljr.properties.ReportProperties;
import com.cee.ljr.utils.FileUtil;

@Component
public class WeeklyStatusReportXlsxGenerator {
	private static final Logger log = LoggerFactory.getLogger(WeeklyStatusReportXlsxGenerator.class);
	
	public static final DateFormat weekEndingDateFormater = new SimpleDateFormat("yyyyMMdd");
	
	@Autowired
	ReportProperties props;
	
	private static final int epicColIndex = 0;
	private static final int storyColIndex = 1;
	private static final int taskSummaryColIndex = 2;
	private static final int runningTotalLabelColIndex = 3;
	private static final int runningTotalValueColIndex = 4;
	private static final int weeklyTotalLabelColIndex = 5;
	private static final int weeklyTotalValueColIndex = 6;
	
	private static CellStyle epicCellStyle;
	private static CellStyle storyCellStyle;
	private static CellStyle taskCellStyle;	
	private static CellStyle epicHoursCellStyle;
	private static CellStyle storyHoursCellStyle;
	private static CellStyle taskHoursCellStyle;
	private static DataFormat dataFormat;
	private static short hoursDataFormat;
	
	/**
	 * Generates the Weekly Status Report spreadsheet from the given report and report path.
	 * @param weeklyStatusReport The report used to generate the document.
	 * @param reportPath The path to save the document to.<br/>
	 * <code>null</code> value will default to the "default.report.path" in the weekly-status-report.properties.
	 */
	public void generateWsrDocument(WeeklyStatusReport weeklyStatusReport, String reportPath) {		
		if (weeklyStatusReport == null) {
			throw new IllegalArgumentException("statusReport must not be null.");
		}
		if (CollectionUtils.isEmpty(weeklyStatusReport.getProjects())) {
			log.error("statusReport must contain at least one project");
			return;
		}
		
		if (weeklyStatusReport.getHoursWorkedBetween() <= 0) {
			log.info("Unable to create report, no hours were logged between {} and {}.", 
			weeklyStatusReport.getWeekStartDate(), weeklyStatusReport.getWeekEndingDate());			
		}
		
		if (reportPath != null && !FileUtil.isValidPath(reportPath)) {
			log.error("report path {} is not a valid path.", reportPath);
			return;
		}
		
		generateSpreadsheet(weeklyStatusReport, reportPath);		
	}
	
	private String buildFilePath(String reportPath, Date weekEndingDate) {
		if (reportPath == null) {
			reportPath = props.getReportPath();
		}
		if (!FileUtil.isValidPath(reportPath)) {
			log.error("report path [{}] is invalid!", reportPath);
			return null;
		}
		String documentName = generateReportName(weekEndingDate);
		
		return Paths.get(reportPath, documentName).toAbsolutePath().toString();
		
	}
	
	private String generateReportName(Date weekEndingDate) {
		String dateString = weekEndingDateFormater.format(weekEndingDate);
		
		return new StringBuilder()
					.append(props.getWeeklyStatusReportTitle())
					.append(" WE ")
					.append(dateString)
					.append(".xlsx")
					.toString();
	}
		
	private void generateSpreadsheet(WeeklyStatusReport weeklyStatusReport, String reportPath) {
		log.info("{} generating.", props.getWeeklyStatusReportTitle());
		Workbook wb = new XSSFWorkbook();
		Date startDate = weeklyStatusReport.getWeekStartDate();
		Date endDate = weeklyStatusReport.getWeekEndingDate();
		initializeCellStyles(wb);
		
		for (Project project : weeklyStatusReport.getProjects()) {
			float hoursWorkedForWeek = project.getHoursWorkedBetween(startDate, endDate);
			//log.debug("Weekly hours worked for project: " + hoursWorkedForWeek);
			if (hoursWorkedForWeek > 0) {
				addProject(wb, project, startDate, endDate);
			}
		}
		
		String filePath = buildFilePath(reportPath, weeklyStatusReport.getWeekEndingDate());
		
		FileOutputStream fileOut = null;
		try {
			
			fileOut = new FileOutputStream(filePath);
	        wb.write(fileOut);
	        fileOut.close();
	        log.info("{} saved to {}.", props.getWeeklyStatusReportTitle(), filePath);
		}
		catch (IOException ioe) {
			log.error("Unable to create new workbook.", ioe);
		}
	}
	
	private void initializeCellStyles(Workbook wb) {
		initializeHoursDataFormat(wb);
		initializeEpicStyles(wb);
		initializeStoryStyles(wb);
		initializeTaskStyles(wb);
	}
	
	private void initializeHoursDataFormat(Workbook wb) {
		dataFormat = wb.createDataFormat();
		hoursDataFormat = dataFormat.getFormat("#,##0.00");		
	}
	
	private void initializeTaskStyles(Workbook wb){
		taskCellStyle = wb.createCellStyle();
		//taskCellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		//taskCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		taskHoursCellStyle = wb.createCellStyle();
		//taskHoursCellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		//taskHoursCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		taskHoursCellStyle.setDataFormat(hoursDataFormat);
	}
	
	private void initializeStoryStyles(Workbook wb){
		storyCellStyle = wb.createCellStyle();
		storyCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		storyCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		storyHoursCellStyle = wb.createCellStyle();
		storyHoursCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		storyHoursCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		storyHoursCellStyle.setDataFormat(hoursDataFormat);
	}
	
	private void initializeEpicStyles(Workbook wb) {
		epicCellStyle = wb.createCellStyle();
		epicCellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		epicCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		epicHoursCellStyle = wb.createCellStyle();
		epicHoursCellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		epicHoursCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		epicHoursCellStyle.setDataFormat(hoursDataFormat);
	}
	
	private void addProject(Workbook wb, Project project, Date startDate, Date endDate) {
		Sheet sheet = wb.createSheet(project.getName());
		MutableInt nextRowIndex = new MutableInt(-1);
		for (Epic epic : project.getEpics()) {
			float hoursWorkedForWeek = epic.getHoursWorkedBetween(startDate, endDate);
			//log.debug("Weekly hours worked for epic: " + hoursWorkedForWeek);
			if (hoursWorkedForWeek > 0) {
				nextRowIndex.add(1);
				addEpic(sheet, nextRowIndex, epic, startDate, endDate);
			}
		}
		// have to autosize after sheet is populated
		autosizeSheet(sheet);
	}
	
	private void autosizeSheet(Sheet sheet) {
		int numberOfColumns = getLastColumnNumber(sheet);
		
		for (int i = 0; i < numberOfColumns; i++) {
			sheet.autoSizeColumn(i);
		}
	}
	
	private int getLastColumnNumber(Sheet sheet) {
		int maxColumnNum = -1;
		int lastRowIndex = sheet.getLastRowNum();
		
		for (int i = 0; i <= lastRowIndex; i++) {
			Row row = sheet.getRow(i);
			short lastColumnNum = row.getLastCellNum();
			if (lastColumnNum > maxColumnNum) {
				maxColumnNum = lastColumnNum;
			}
		}
		return maxColumnNum;
	}
	
	private void addEpic(Sheet sheet, MutableInt nextRowIndex, Epic epic, Date startDate, Date endDate) {
		int rowIndex = nextRowIndex.intValue();
		Row row = sheet.createRow(rowIndex);
		Cell cell = row.createCell(epicColIndex);
		cell.setCellValue(epic.getName());
		cell.setCellStyle(epicCellStyle);
		
		mergeCellsInRow(sheet, rowIndex, epicColIndex, taskSummaryColIndex);		
		
		addRunningTotal(row, epic.getTotalHoursWorked(), epicCellStyle, epicHoursCellStyle, true);
		addWeeklyTotal(row, epic.getHoursWorkedBetween(startDate, endDate), epicCellStyle, epicHoursCellStyle, true);
		
		for (Story story : epic.getStories()) {
			float hoursWorkedForWeek = story.getHoursWorkedBetween(startDate, endDate);
			//log.debug("Weekly hours worked for story: " + hoursWorkedForWeek);
			if (hoursWorkedForWeek > 0) {
				nextRowIndex.add(1);
				addStory(sheet, nextRowIndex, story, startDate, endDate);
			}
		}
	}
	
	private void addStory(Sheet sheet, MutableInt nextRowIndex, Story story, Date startDate, Date endDate) {
		int storyRowIndex = nextRowIndex.intValue();
		
		Row row = sheet.createRow(storyRowIndex);
		Cell cell = row.createCell(storyColIndex);
		cell.setCellValue(story.getName());
		cell.setCellStyle(storyCellStyle);
		
		mergeCellsInRow(sheet, storyRowIndex, storyColIndex, taskSummaryColIndex);		
		
		addRunningTotal(row, story.getTotalHoursWorked(), storyCellStyle, storyHoursCellStyle, false);
		addWeeklyTotal(row, story.getHoursWorkedBetween(startDate, endDate), storyCellStyle, storyHoursCellStyle, false);
		
		for (Task task : story.getTasks()) {
			float hoursWorkedForWeek = task.getHoursWorkedBetween(startDate, endDate);
			//log.debug("Weekly hours worked for task: " + hoursWorkedForWeek);
			if (hoursWorkedForWeek > 0) {
				nextRowIndex.add(1);
				addTask(sheet, nextRowIndex, task, startDate, endDate);
			}
		}
		//Group the Rows together
        sheet.groupRow(storyRowIndex+1,nextRowIndex.intValue());
        sheet.setRowGroupCollapsed(storyRowIndex+1, true);
	}
	
	private void addTask(Sheet sheet, MutableInt nextRowIndex, Task task, Date startDate, Date endDate) {
		Row row = sheet.createRow(nextRowIndex.intValue());
		Cell cell = row.createCell(taskSummaryColIndex);
		cell.setCellValue(task.getSummary());
			
		addRunningTotal(row, task.getTotalHoursWorked(), taskCellStyle, taskHoursCellStyle, false);
		addWeeklyTotal(row, task.getHoursWorkedBetween(startDate, endDate), taskCellStyle, taskHoursCellStyle, false);
	}
	
	private void addRunningTotal(Row row, float value, CellStyle labelCellStyle, CellStyle valueCellStyle, boolean showLabel) {
		Cell cell = row.createCell(runningTotalLabelColIndex);
		if (labelCellStyle != null) {
			cell.setCellStyle(labelCellStyle);
		}
		if (showLabel) {
			cell.setCellValue("Running Total:");
		}
		
		cell = row.createCell(runningTotalValueColIndex);
		cell.setCellValue(value);
		if (valueCellStyle != null) {
			cell.setCellStyle(valueCellStyle);
		}
	}
	
	private void addWeeklyTotal(Row row, float value,  CellStyle labelCellStyle, CellStyle valueCellStyle, boolean showLabel) {
		Cell cell = row.createCell(weeklyTotalLabelColIndex);
		if (labelCellStyle != null) {
			cell.setCellStyle(labelCellStyle);
		}
		if (showLabel) {
			cell.setCellValue("Weekly Total:");
		}
		
		cell = row.createCell(weeklyTotalValueColIndex);
		cell.setCellValue(value);
		if (valueCellStyle != null) {
			cell.setCellStyle(valueCellStyle);
		}
	}
	

	
	private void mergeCellsInRow(Sheet sheet, int rowIndex, int beginCellIndex, int endCellIndex) {
		sheet.addMergedRegion(new CellRangeAddress(
				rowIndex, //first row (0-based)
				rowIndex, //last row  (0-based)
				beginCellIndex, //first column (0-based)
				endCellIndex  //last column  (0-based)
	    ));
	}
	
}
