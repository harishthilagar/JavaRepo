package com.gowtham.fileWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.gowtham.contants.UIConstants;

public class FileWriters {

	public void writeMethod(String value) {
		String filePath = UIConstants.USER_DIR + "//src//test//resources//NonPap.txt";

		File file = new File(filePath);
		try {
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(value);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
