package cn.tjucic.selenium;

import static org.junit.Assert.*;
import org.junit.*;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestStudents {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	class Student {
		public String id;
		public String pwd;
		public String name;
		public String url;
	}

	@Before
	public void setUp() throws Exception {
		String driverPath = System.getProperty("user.dir") + "/src/resources/driver/geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		baseUrl = "http://121.193.130.195:8800/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testStudents() throws Exception {
		Student[] stu = new Student[143];
		for (int i = 0; i < 143; i++) {
			stu[i] = new Student();
		}
		
		// 定义工作簿
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(new FileInputStream("D:\\软件测试名单.xlsx"));
		} catch (Exception e) {
			System.out.println("Excel data file cannot be found!");
		}
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell idCell = row.getCell(1);
			String id = NumberToTextConverter.toText(idCell.getNumericCellValue());
			String name = row.getCell(2).getStringCellValue();
			String url = row.getCell(3).getStringCellValue();
			String pwd = id.substring(4);
			stu[i - 2].id = id;
			stu[i - 2].pwd = pwd;
			stu[i - 2].name = name;
			stu[i - 2].url = url;
		}

		driver.get(baseUrl + "/");
		for (int k = 0; k < 143; k++) {
			driver.findElement(By.name("id")).clear();
			driver.findElement(By.name("id")).sendKeys(stu[k].id);
			driver.findElement(By.name("password")).clear();
			driver.findElement(By.name("password")).sendKeys(stu[k].pwd);
			driver.findElement(By.id("btn_login")).click();
			assertEquals(stu[k].id, driver.findElement(By.id("student-id")).getText());
			assertEquals(stu[k].name, driver.findElement(By.id("student-name")).getText());
			assertEquals(stu[k].url, driver.findElement(By.id("student-git")).getText());
			driver.findElement(By.id("btn_logout")).click();
			driver.findElement(By.id("btn_return")).click();
		}

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
}
