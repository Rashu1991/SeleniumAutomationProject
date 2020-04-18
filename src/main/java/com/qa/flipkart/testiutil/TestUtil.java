package com.qa.flipkart.testiutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import com.qa.flipkart.testbase.TestBase;

public class TestUtil extends TestBase {

	static FileInputStream fis;
	static Workbook book;static Sheet sheet;
	public static void waitForPageLoad(WebDriver driver) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(30000))
				.pollingEvery(Duration.ofMillis(5000))
				.ignoring(NoSuchElementException.class);

		boolean loaded=wait.until(new Function<WebDriver, Boolean>() {

			public Boolean apply(WebDriver driver) {
				System.out.println("Interval time " + new Date());
				
				((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				System.out.println("Interval time " + new Date());
				return true;
			
				
			}
		});
		
		if(loaded) {
			System.out.println("Page is fully loaded");
		}

	}
	
	
	public static void getScreenshotOfEntirePage() {
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDirectory =System.getProperty("user.dir");
		try {
			FileUtils.copyFile(srcFile, new File(currentDirectory+"/screenshotsPage/"+System.currentTimeMillis()+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public static void getScreenshotOfElement(WebElement ele) {
		File srcFile=ele.getScreenshotAs(OutputType.FILE);
		String currDir = System.getProperty("user.dir");
		try {
			FileUtils.copyFile(srcFile, new File(currDir+"/screenshotsEle/"+System.currentTimeMillis()+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Object[][] getData(String sheetName) throws EncryptedDocumentException, IOException{
		
		fis = new FileInputStream("C:\\Users\\Mudit\\eclipse-workspace"
				+ "\\POMAutomationPractice\\src\\main\\java\\com\\qa\\flipkart\\testdata\\testData.xlsx");
		
		book=WorkbookFactory.create(fis);
		sheet = book.getSheet(sheetName);
		Object [][]data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int k=0;k<sheet.getRow(0).getLastCellNum();k++) {
				data[i][k]=sheet.getRow(i+1).getCell(k).toString();
			}
		}
		
		return data;
		
	}
	

}
