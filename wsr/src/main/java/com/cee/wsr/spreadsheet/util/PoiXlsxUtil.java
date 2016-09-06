package com.cee.wsr.spreadsheet.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility for processing {@code org.apache.poi} xlsx objects.
 *
 */
public final class PoiXlsxUtil {

	private static final Logger LOG = LoggerFactory.getLogger(PoiXlsxUtil.class);
	
	private PoiXlsxUtil() {
	}

	/**
	 * Gets the cell value.
	 * <p>
	 * Cell contents must be the one of the following types:<br>
	 * {@link org.apache.poi.ss.usermodel.Cell#CELL_TYPE_STRING}<br>
	 * {@link org.apache.poi.ss.usermodel.Cell#CELL_TYPE_NUMERIC}<br>
	 * {@link org.apache.poi.ss.usermodel.Cell#CELL_TYPE_BOOLEAN}<br>
	 * {@link org.apache.poi.ss.usermodel.Cell#CELL_TYPE_FORMULA}
	 * <p>
	 * @param cell  the Cell parameter
	 * @return  the String representation of the cell value, empty String if value is not type listed above or null
	 */
	public static String getCellValue(Cell cell) {
		String string = "";
		if(cell == null) {
			return string;
		}
		
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				string = cell.getRichStringCellValue().getString();
				//LOG.debug(cell.getAddress().formatAsString() + ", Cell.CELL_TYPE_STRING: " + string);
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					string = String.valueOf(cell.getDateCellValue());
					//LOG.debug(cell.getAddress().formatAsString() + ", Cell.CELL_TYPE_NUMERIC: " + string);
				} else {
					string = String.valueOf(cell.getNumericCellValue());
					//LOG.debug(cell.getAddress().formatAsString() + ", Cell.CELL_TYPE_NUMERIC: " + string);
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				string = String.valueOf(cell.getBooleanCellValue());
				//LOG.debug(cell.getAddress().formatAsString() + ", Cell.CELL_TYPE_BOOLEAN: " + string);
				break;
			case Cell.CELL_TYPE_FORMULA:
				string = cell.getCellFormula();
				//LOG.debug(cell.getAddress().formatAsString() + ", Cell.CELL_TYPE_FORMULA: " + string);
				break;
			default:
				string = "";
				//LOG.debug(cell.getAddress().formatAsString() + ", default: " + string);
		}

		return string;
	}
	

	
	/**
	 * Gets the cell value without leading or trailing whitespaces.
	 * <p>
	 * Cell contents must be the one of the following types:<br>
	 * {@link org.apache.poi.ss.usermodel.Cell#CELL_TYPE_STRING}<br>
	 * {@link org.apache.poi.ss.usermodel.Cell#CELL_TYPE_NUMERIC}<br>
	 * {@link org.apache.poi.ss.usermodel.Cell#CELL_TYPE_BOOLEAN}<br>
	 * {@link org.apache.poi.ss.usermodel.Cell#CELL_TYPE_FORMULA}
	 * <p>
	 * @param cell  the Cell parameter
	 * @return  the String representation of the cell value, empty String if value is null, only whitespaces, 
	 * 			or not type listed above
	 */
	public static String getStrippedCellValue(Cell cell) {
		String content = getCellValue(cell);
		
		return StringUtils.stripToEmpty(content);
	}
	
	/**
	 * Returns the content of cells in the given row for each given cellNumber.
	 * @param row The row.
	 * @param cellNumbers The cell numbers.
	 * @return List String The contents of the cells.
	 */
	public static List<String> getStringValues(Row row, Set<Integer> cellNumbers) {
		List<String> stringValues = new ArrayList<String>();
		for (Integer cellNumber : cellNumbers) {
			Cell cell = row.getCell(cellNumber);
			String value = getStrippedCellValue(cell);
			if (StringUtils.isNotBlank(value)) {
				stringValues.add(value);
			}
		}
			
		return stringValues;
	}
	
	public static String getStringValue(Row row, Integer cellNumber) {
		Cell cell = row.getCell(cellNumber);
		return getStrippedCellValue(cell);
	}
}
