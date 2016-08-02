package com.cee.wsr.spreadsheet;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 * Utility for processing {@code org.apache.poi} xlsx objects.
 *
 */
public final class PoiXlsxUtil {

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
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				string = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					string = String.valueOf(cell.getDateCellValue());
				} else {
					string = String.valueOf(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				string = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				string = cell.getCellFormula();
				break;
			default:
				string = "";
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
}
