package com.xcover.test;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cba.CommonLayer.BaseClass;
import com.cba.PageObjectLayer.GetQuote;

public class CoverGeniusTest  extends BaseClass{
	WebDriver driver;
	private String url=readProperty("url");

	@BeforeTest
	public void initialise()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.navigate().to(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);

	}


	@Test(priority = 1)
	public  void ValidateMainScreen(){
		GetQuote quote=new GetQuote(driver);
		quote.clickGetQuoteButton();
		quote.selectCategory();
	}

	@Test(priority = 2)
	public  void test_ValidateEmptyQuery(){
		GetQuote quote=new GetQuote(driver);
		quote.enterAdress();
		quote.enterBrand();
		quote.enterModel();

	}

	@Test(priority = 3)
	public  void test_ValidateresultForQuery(){
		GetQuote quote=new GetQuote(driver);
		quote.enterAmountPaid();
		quote.enterDateBought();
		quote.enterCondition();

	}


	@AfterTest

	public void teardown() {
		driver.quit();

	}
}
