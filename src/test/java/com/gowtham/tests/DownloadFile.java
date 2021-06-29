package com.gowtham.tests;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.gowtham.contants.UIConstants;
import com.gowtham.dataprovider.DataProvider2;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DownloadFile {

	public static void main(String[] args) {
		setup();
		DataProvider2 dp = new DataProvider2();
		LinkedList<Integer> list = dp.getData();
		write(list);
		tearDown();

	}

	public static WebDriver driver;

	public static void setup() {
		String downloadFilepath = "D:\\attach\\"+UIConstants.EXCEL_NAME;
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public static void write(LinkedList<Integer> list) {
		driver.get(UIConstants.BASE_URL);
		
		WebElement username = driver.findElement(By.xpath("//input[@id='ctl00_SmartMasterContent_rtbuserlogin']"));
		username.sendKeys(UIConstants.USERNAME);
		
		WebElement password = driver.findElement(By.xpath("//input[@id='ctl00_SmartMasterContent_rtbpasswd']"));
		password.sendKeys(UIConstants.PASSWORD);
		
		driver.findElement(By.xpath("//input[@id='ctl00_SmartMasterContent_rblogin_input']")).click();
		
		for (int value : list) {
			driver.get(UIConstants.BASE_URL1+ value);
			WebElement attachment = driver.findElement(By.xpath("//li[@class='rtsLI'][3]"));
			attachment.click();
		    
			List<WebElement> table = driver.findElements(By.xpath("//table[@id='ctl00_ArticleAttachmentGrid_uc_ArticleAttachmentGridEdit_ctl00']//tbody//tr"));
			
			for (int i = 1; i <= table.size(); i++) {
				WebElement tr = driver.findElement(By.xpath("//table[@id='ctl00_ArticleAttachmentGrid_uc_ArticleAttachmentGridEdit_ctl00']//tr["+ i + "]//td[3]"));
				String tdText = tr.getText();	
				if (tdText.contains("OnlineFirst") && !tdText.contains("OnlineFirst PDF")) {
					driver.findElement(By.xpath("//table[@id='ctl00_ArticleAttachmentGrid_uc_ArticleAttachmentGridEdit_ctl00']//tr["+ i + "]//td[1]//input")).click();
				}
			}
			
			driver.findElement(By.xpath("//a[@class='rightAligned rtbWrap']")).click();

			try {
				Thread.sleep(UIConstants.SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void tearDown() {
		driver.close();
		driver.quit();
	}

}
