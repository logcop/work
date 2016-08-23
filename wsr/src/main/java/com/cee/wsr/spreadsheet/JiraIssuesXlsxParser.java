package com.cee.wsr.spreadsheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.cee.wsr.domain.JiraIssue;
import com.cee.wsr.domain.JiraIssues;
@Component
public class JiraIssuesXlsxParser {
	private static final int HEADER_ROW = 0;
	private static final int START_ROW = 1;
	/*private static final int PROJECT_COL = 0;
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
	private static final int PROJECT_COL = 7;
	private static final int KEY_COL = 1;
	private static final int SUMMARY_COL = 0;
	private static final int TYPE_COL = 4;
	private static final int STATUS_COL = 5;
	private static final int EST_TIME_COL = 34;
	private static final int LOGGED_TIME_COL = 36;
	private static final int SUB_TASK_COL = 24;
	private static final int LINKED_ISSUES_COL = 26;
	private static final int DEV_COL = 37;//41;
	private static final int EPIC_COL = 39;//49;

	private Map<String, Set<Integer>> attrToColIndexMap;*/

	private Map<Integer, String> indexToAttributeNameMap;
	
	
	public JiraIssues parseXlsx(String[] xlsPaths) {
		JiraIssues jiraIssues = new JiraIssues();
		for (String xlsPath : xlsPaths) {
			try {
				FileInputStream file = new FileInputStream(new File(xlsPath));
				XSSFWorkbook workbook = new XSSFWorkbook(file);
	
				XSSFSheet sheet = workbook.getSheetAt(0);
				
				indexToAttributeNameMap = createIndexMap(sheet);
				
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
	
	/*private Map<String, Set<Integer>> initializeIssuesAttributesMap(XSSFSheet sheet) {
		Row attributeRow = sheet.getRow(HEADER_ROW);
		int numOfCellsInRow = attributeRow.getLastCellNum();
		
		Map<String, Set<Integer>> attributePositionsMap = new HashMap<String, Set<Integer>>(numOfCellsInRow, 1.0f);
		for (Cell cell : attributeRow) {
			String attributeName = PoiXlsxUtil.getStrippedCellValue(cell);
			Set<Integer> positions = null;
			if (attributePositionsMap.containsKey(attributeName)) {
				positions = attributePositionsMap.get(attributeName);
				positions.add(cell.getColumnIndex());
			}
			else {
				positions = new HashSet<Integer>();
			}
		}
		return attributePositionsMap;
	}*/
	
	/**
	 * Gets the cell number for the given issueAttribute.
	 * @param issueAttribute The attribute to get from the issue.
	 * @return the cell number containing the data for the given issueAttribute. 
	 * <code>null</code> if issueAttribute unsupported.
	 */
	/*private Integer getAttributeCellNumber(String issueAttribute) {
		if (attrToColIndexMap.containsKey(issueAttribute)) {
			return attrToColIndexMap.get(issueAttribute).iterator().next();
		}
		else {
			return null;
		}
		
	}*/
	
	/**
	 * Gets the cell numbers for the given issueAttribute.
	 * @param issueAttribute The attribute to get from the issue.
	 * @return the cell numbers containing the data for the given issueAttribute.
	 */
	/*private Set<Integer> getAttributeCellNumbers(String issueAttribute) {
		if (attrToColIndexMap.containsKey(issueAttribute)) {
			return attrToColIndexMap.get(issueAttribute);
		}
		else {
			return new HashSet<Integer>();
		}
		
	}*/
	
	/*private String getStringValue(Row row, String jiraIssueAttribute) {
		Integer cellNumber = getAttributeCellNumber(jiraIssueAttribute);
		String value = PoiXlsxUtil.getStringValue(row, cellNumber);
		return value;
	}
	
	private List<String> getStringValues(Row row, String jiraIssueAttribute) {		
		Set<Integer> cellNumbers = getAttributeCellNumbers(jiraIssueAttribute);
		List<String> stringValues = PoiXlsxUtil.getStringValues(row, cellNumbers);
		return stringValues;
	}*/
	
	/*private JiraIssue process(Row row) {
		JiraIssue jiraIssue = new JiraIssue();
		
		//String projectName = PoiXlsxUtil.getStrippedCellValue(row.getCell(PROJECT_COL));//attrToColIndexMap
		String projectName = getStringValue(row, JiraAttribute.PROJECT_NAME);
		jiraIssue.setProjectName(projectName);
		
		//String key = PoiXlsxUtil.getStrippedCellValue(row.getCell(KEY_COL));
		String key = getStringValue(row, JiraAttribute.ISSUE_KEY);
		jiraIssue.setKey(key);
		
		//String summary = PoiXlsxUtil.getStrippedCellValue(row.getCell(SUMMARY_COL));
		String summary = getStringValue(row, JiraAttribute.SUMMARY);
		jiraIssue.setSummary(summary);
		
		//String type = PoiXlsxUtil.getStrippedCellValue(row.getCell(TYPE_COL));
		String type = getStringValue(row, JiraAttribute.ISSUE_TYPE);
		jiraIssue.setType(type);
		
		//String status = PoiXlsxUtil.getStrippedCellValue(row.getCell(STATUS_COL));
		String status = getStringValue(row, JiraAttribute.STATUS);
		jiraIssue.setStatus(status);
		
		//String estimate = PoiXlsxUtil.getStrippedCellValue(row.getCell(EST_TIME_COL));
		String estimate = getStringValue(row, JiraAttribute.ORIGINAL_ESTIMATE);
		if (StringUtils.isNumeric(estimate)) {
			jiraIssue.setEstimate(new Float(estimate)/3600);
		}
		
		//String timeSpent = PoiXlsxUtil.getStrippedCellValue(row.getCell(LOGGED_TIME_COL));
		String timeSpent = getStringValue(row, JiraAttribute.TIME_SPENT);
		if (StringUtils.isNumeric(timeSpent)) {
			jiraIssue.setTimeSpent(new Float(timeSpent)/3600);
		}
		
		//String subTasks = PoiXlsxUtil.getStrippedCellValue(row.getCell(SUB_TASK_COL));
		//List<String> subTasks = getStringValues(row, JiraAttribute.);
		//jiraIssue.setSubTasks(StringUtils.split(subTasks, ','));
		
		//String linkedIssues = PoiXlsxUtil.getStrippedCellValue(row.getCell(LINKED_ISSUES_COL));
		List<String> linkedIssues = getStringValues(row, JiraAttribute.OUTWARD_ISSUE_LINK_PROBLEM_INCIDENT);
		//jiraIssue.setSubTasks(StringUtils.split(linkedIssues, ','));
		jiraIssue.setLinkedIssueKeys(new HashSet<String>(linkedIssues));
		
		//String epic = PoiXlsxUtil.getStrippedCellValue(row.getCell(EPIC_COL));
		String epic = getStringValue(row, JiraAttribute.CUSTOM_FIELD_EPIC_NAME);
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
		
	}*/
}
