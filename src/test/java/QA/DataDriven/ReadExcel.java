package QA.DataDriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ReadExcel {

	WebDriver driver;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell;
	
	
	@Test
	public void fbLogin() throws IOException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\AXIS_TRAINING\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		System.out.println("InvokeChrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
		
		//Import excel sheet
		
		File src = new File("D:\\AXIS_TRAINING\\eclipse-workspace\\DataDriven\\TestData.xlsx");
		
		// load the file
		
		FileInputStream fis = new FileInputStream(src);
		
		//load the workbook
		
		workbook = new XSSFWorkbook(fis);
		
		// access the sheet from workbook
		
		sheet = workbook.getSheetAt(0);
		
		for(int i=1;i<=sheet.getLastRowNum();i++){
			//import the data for email
			
			cell=sheet.getRow(i).getCell(0);
			System.out.println(cell);
			driver.findElement(By.xpath("//input[@id='email']")).clear();
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys(cell.getStringCellValue());
			
			
			//import the data for password
			cell=sheet.getRow(i).getCell(1);
			System.out.println(cell);
			driver.findElement(By.xpath("//input[@id='pass']")).clear();
			driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(cell.getStringCellValue());
			
			
		}
	}
}
