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

	public LinkedList<Integer> getData() {
		String filePath = UIConstants.EXCEL_PATH+UIConstants.EXCEL_NAME+".xlsx";
		try {
			fis = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fis);			
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet ws = wb.getSheet("sheet1");
		LinkedList<Integer> list = new LinkedList<Integer>();
		Iterator<Row> ri = ws.rowIterator();
		while (ri.hasNext()) {
			Row contentRow = ri.next();
			Iterator<Cell> ci = contentRow.cellIterator();
			while (ci.hasNext()) {
				Cell curCell = ci.next();
				int value =(int) curCell.getNumericCellValue();
				list.add(value);
			}
		}
		return list;

	}
}
