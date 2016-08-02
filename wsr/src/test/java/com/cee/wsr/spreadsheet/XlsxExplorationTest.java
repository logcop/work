package com.cee.wsr.spreadsheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxExplorationTest extends TestCase {
	private static final String xlsPath = "C:/Users/chuck/Desktop/JIRA.xlsx";

	public void testShowPositioning() {
		try {
			FileInputStream file = new FileInputStream(new File(xlsPath));
			// Get the workbook instance for XLS file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				for (Cell cell : row) {
					CellReference cellRef = new CellReference(row.getRowNum(),
							cell.getColumnIndex());
					System.out.print(cellRef.formatAsString() + " "
							+ cell.getColumnIndex());
					System.out.print(" - ");

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						System.out.println(cell.getRichStringCellValue()
								.getString());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							System.out.println(cell.getDateCellValue());
						} else {
							System.out.println(cell.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						System.out.println(cell.getCellFormula());
						break;
					default:
						System.out.println();
					}
				}
			}
			workbook.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
