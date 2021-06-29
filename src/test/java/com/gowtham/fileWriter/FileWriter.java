package com.gowtham.fileWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gowtham.contants.UIConstants;

public class FileWriter {
	XSSFWorkbook wb;

	public void writeMethod(String value, int i, int j) {
		String filePath = UIConstants.USER_DIR + "//src//test//resources//myDemo.xlsx";
		FileInputStream fis;
		
		try {
			fis = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fis);
			fis.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(wb.getNumberOfSheets());
		XSSFSheet ws = wb.getSheet("mySheet");
		System.out.println(ws.getSheetName());
		ws.createRow(3).createCell(3).setCellValue(value);

		try {
			wb.write(new FileOutputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
