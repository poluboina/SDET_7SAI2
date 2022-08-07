package com.VTigerTest.generic;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.ObjectRepo.POM.HomePage;
import com.ObjectRepo.POM.LoginPage;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseUtility implements IAutoconstants{

	
	
	
	public WebDriver driver;
	public static WebDriver sdriver;
	
	PropertyFile propertyFile = PropertyFile.getObjectProperty();		
	
	

	@BeforeSuite(groups = {"smoke","regression"})
	public void connectDatabase() {

		System.out.println("Database is coonected");	
	}

	@BeforeClass(groups = {"smoke","regression"})
	public void lanchBrowser() throws Throwable {

		

		String BROWSER=propertyFile.readDatafromPropertyfile("browser");
		if(BROWSER.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();

		}
		else {
			System.out.println("invalid input");
		}
	
	
	
		Webdriver_Utility dUtil=new Webdriver_Utility(driver);
		dUtil.maximumWindow();
		dUtil.pageLoadTimeout();
		driver.get(propertyFile.readDatafromPropertyfile("url"));
		sdriver=driver;
	}

	@BeforeMethod(groups = {"smoke","regression"})
	public void login() throws IOException {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.getUsernametextbox().sendKeys(PropertyFile.getObjectProperty().readDatafromPropertyfile("username"));
		loginpage.getPasswordtextbox().sendKeys(PropertyFile.getObjectProperty().readDatafromPropertyfile("password"));
		loginpage.getLoginbtn().click();

	}
	
	@AfterMethod(groups = {"smoke","regression"})
	public void logout() {

		HomePage homepage = new HomePage(driver);
		homepage.getSignoutimg().click();
		homepage.getSignoutlink();
	}

	@AfterClass(groups = {"smoke","regression"})
	public void closingBrowser() 
	{
		Webdriver_Utility dUtil=new Webdriver_Utility(driver);
		dUtil.closeBrowser();
	}

	@AfterSuite(groups = {"smoke","regression"})
	public void closeDatabase() {

		System.out.println("closeDataBase");
	}

	public  static String screenshot(String name) throws IOException {
		
			TakesScreenshot ts = (TakesScreenshot)BaseUtility.sdriver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			String path = IAutoconstants.screenshotpath+name+".png";
			File dest = new File(path);
			Files.copy(src, dest);
			return path;

		}
		
	}


