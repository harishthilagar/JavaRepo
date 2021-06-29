package com.gowtham.dataprovider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gowtham.contants.UIConstants;

public class DataProvider2 {
	XSSFWorkbook wb;
	FileInputStream fis;

	public LinkedList<String> getData() {
		String filePath = UIConstants.EXCEL_PATH + UIConstants.EXCEL_NAME + ".xlsx";
		try {
			fis = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet ws = wb.getSheet("sheet1");
		LinkedList<String> list = new LinkedList<String>();
		Iterator<Row> ri = ws.rowIterator();
		while (ri.hasNext()) {
			Row contentRow = ri.next();
			Iterator<Cell> ci = contentRow.cellIterator();
			while (ci.hasNext()) {
				Cell curCell = ci.next();
				int value = (int) curCell.getNumericCellValue();
				String value1 = String.valueOf(value);
				if (value1.length() >= 6) {
					list.add(value1);
				}
			}
		}
		return list;

	}
}
