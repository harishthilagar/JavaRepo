package com.gowtham.dataprovider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.gowtham.contants.UIConstants;


public class DataProvider1 {

	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet ws;
	String filePath = UIConstants.USER_DIR + "\\src\\test\\resources\\testdata\\TestData.xlsx";
	LinkedList<LinkedList<String>> addressList;
	LinkedList<String> temp ;
	
    @DataProvider(name ="addressDataProvider")
	public Iterator<Object> getExcelSheetData() {
		LinkedList<Object> objList = new LinkedList<Object>();
		try {
			fis = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fis);
			int noOfSheets = wb.getNumberOfSheets();
			for (int i = 0; i < noOfSheets; i++) {
				ws = wb.getSheetAt(i);
				if (ws.getSheetName().equalsIgnoreCase("Addresses")) {
					Iterator<Row> ri = ws.rowIterator();
					Row header = ri.next();
					while (ri.hasNext()) {
						Row contentRow = ri.next();
						Iterator<Cell> ci = contentRow.cellIterator();
						if (ci.next().getStringCellValue().equalsIgnoreCase("Yes")) {
							temp = new LinkedList<String>();
							while (ci.hasNext()) {
								Cell currentCell = ci.next();
								String cellValue = currentCell.getStringCellValue();
								temp.add(cellValue);
							}
							Object a = new Object();
							a = (Object) temp;
							objList.add(a);
						}
					}

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objList.iterator();
	}

}
