package com.cee.wsr.spreadsheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.cee.wsr.domain.JiraIssue;
import com.cee.wsr.domain.JiraIssues;
import com.cee.wsr.domain.Project;
import com.cee.wsr.utils.DevNameUtil;
@Component
public class JiraIssuesXlsxParser {
	private static final int START_ROW = 4;
	private static final int PROJECT_COL = 0;
	private static final int KEY_COL = 1;
	private static final int SUMMARY_COL = 2;
	private static final int TYPE_COL = 3;
	private static final int STATUS_COL = 4;
	private static final int EST_TIME_COL = 21;
	private static final int LOGGED_TIME_COL = 23;
	private static final int SUB_TASK_COL = 24;
	private static final int LINKED_ISSUES_COL = 26;
	private static final int DEV_COL = 37;//41;
	private static final int EPIC_COL = 39;//49;


	private JiraIssue process(Row row) {
		JiraIssue jiraIssue = new JiraIssue();
		
		String projectName = PoiXlsxUtil.getStrippedCellValue(row.getCell(PROJECT_COL));
		jiraIssue.setProjectName(projectName);
		
		String key = PoiXlsxUtil.getStrippedCellValue(row.getCell(KEY_COL));
		jiraIssue.setKey(key);
		
		String summary = PoiXlsxUtil.getStrippedCellValue(row.getCell(SUMMARY_COL));
		jiraIssue.setSummary(summary);
		
		String type = PoiXlsxUtil.getStrippedCellValue(row.getCell(TYPE_COL));
		jiraIssue.setType(type);
		
		String status = PoiXlsxUtil.getStrippedCellValue(row.getCell(STATUS_COL));
		jiraIssue.setStatus(status);
		
		String estimate = PoiXlsxUtil.getStrippedCellValue(row.getCell(EST_TIME_COL));
		jiraIssue.setEstimate(new Float(estimate)/3600);
		
		String timeSpent = PoiXlsxUtil.getStrippedCellValue(row.getCell(LOGGED_TIME_COL));
		jiraIssue.setTimeSpent(new Float(timeSpent)/3600);
		
		String subTasks = PoiXlsxUtil.getStrippedCellValue(row.getCell(SUB_TASK_COL));
		jiraIssue.setSubTasks(StringUtils.split(subTasks, ','));
		
		String linkedIssues = PoiXlsxUtil.getStrippedCellValue(row.getCell(LINKED_ISSUES_COL));
		jiraIssue.setSubTasks(StringUtils.split(linkedIssues, ','));
		
		String epic = PoiXlsxUtil.getStrippedCellValue(row.getCell(EPIC_COL));
		if (StringUtils.isEmpty(epic)) {
			epic = "Uncategorized";
		}
		
		String projectAbbr = Project.getProjectAbbr(projectName);
		if (StringUtils.isNotBlank(projectAbbr)) {
			epic += " (" + Project.getProjectAbbr(projectName) + ")";
		}
		jiraIssue.setEpic(epic);
		
		
		String[] developers = StringUtils.split(PoiXlsxUtil.getStrippedCellValue(row.getCell(DEV_COL)), ",");
		Set<String> devSet = new HashSet<String>();
		for (int i = 0; i < developers.length; i++) {
			devSet.add(DevNameUtil.getFullName(developers[i]));
		}
		jiraIssue.setDevelopers(devSet);
		
		return jiraIssue;
	}
	
	@Deprecated
	public JiraIssues parseXlsx(String xlsPath) {
		JiraIssues jiraIssues = new JiraIssues();
		try {
			FileInputStream file = new FileInputStream(new File(xlsPath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {				
				if (row.getRowNum() >= START_ROW) {
					
					jiraIssues.addIssue(process(row));
				}
			}
			workbook.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return jiraIssues;
	}
}
