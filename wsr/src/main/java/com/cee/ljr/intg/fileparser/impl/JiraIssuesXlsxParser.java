package com.cee.ljr.intg.fileparser.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.cee.ljr.document.xslx.PoiXlsxUtil;
import com.cee.ljr.intg.jira.domain.JiraIssue;
@Component
public class JiraIssuesXlsxParser {
	private static final int HEADER_ROW = 0;
	private static final int START_ROW = 1;

	private Map<Integer, String> indexToAttributeNameMap;
	
	
	public List<JiraIssue> parseXlsx(String[] xlsPaths) {
		List<JiraIssue> jiraIssues = new ArrayList<JiraIssue>();
		
		for (String xlsPath : xlsPaths) {
			List<JiraIssue> issuesToAdd = parseXlsx(xlsPath);
			jiraIssues.addAll(issuesToAdd);
		}
		
		return jiraIssues;
	}	
	
	
	public List<JiraIssue> parseXlsx(String xlsPath) {
		List<JiraIssue> jiraIssues = new ArrayList<JiraIssue>();
		try {
			FileInputStream file = new FileInputStream(new File(xlsPath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(0);
			
			indexToAttributeNameMap = createIndexMap(sheet);
			
			for (Row row : sheet) {				
				if (row.getRowNum() >= START_ROW) {					
					jiraIssues.add(process(row));
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
	
	private JiraIssue process(Row row) {
		JiraIssue jiraIssue = new JiraIssue();
		
		for (Cell cell : row) {
			int index = cell.getColumnIndex();
			String attributeName = indexToAttributeNameMap.get(index);
			String value = PoiXlsxUtil.getStringValue(row, index);
			jiraIssue.addAttribute(attributeName, value);
		}
		
		return jiraIssue;
	}
	
	
	private Map<Integer, String> createIndexMap(XSSFSheet sheet) {
		Row attributeRow = sheet.getRow(HEADER_ROW);
		int numOfCellsInRow = attributeRow.getLastCellNum();
		
		Map<Integer, String> indexToAttributeMap = new HashMap<Integer, String>(numOfCellsInRow, 1.0f);
		for (Cell cell : attributeRow) {
			String attributeName = PoiXlsxUtil.getStrippedCellValue(cell);
			Integer index = cell.getColumnIndex();
			indexToAttributeMap.put(index, attributeName);
		}
		
		return indexToAttributeMap;
	}
}
