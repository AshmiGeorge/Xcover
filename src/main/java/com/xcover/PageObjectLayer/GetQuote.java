package com.cba.PageObjectLayer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import com.cba.CommonLayer.BaseClass;


public class GetQuote extends BaseClass {


	@FindBy(xpath = "//a[text()='Get A Quote']")
	private static WebElement BTN_getAQuote;
	@FindBy(xpath = "//span[text()='What would you like to insure?']")
	private static WebElement TXT_productCategory;
	@FindBy(xpath = "//span[text()='appliances']/parent::button")
	private static WebElement BTN_appliances;
	@FindBy(xpath = "//form/div//button[text()='Next']")
	private static WebElement BTN_next;
	@FindBy(xpath = "//span[text()='Where do you live?']")
	private static WebElement TXT_location;
	@FindBy(xpath = "//p[text()='Enter your full address']/parent::header/following::input")
	private static WebElement TXTAREA_fullAddress;
	@FindBy(xpath = "//ul[@role='listbox']//li[1]")
	private static WebElement list_typeAheadAddress;

	@FindBy(xpath = "//p/parent::header/following::input")
	private static WebElement TXTAREA_manufacturer;
	@FindBy(xpath = "//p[text()='Enter the brand/manufacturer that made your product']")
	private static WebElement LABEL_manufacturer;
	@FindBy(xpath = "//p[text()='Enter the model & version of your product']")
	private static WebElement LABEL_model;
	@FindBy(xpath = "//p[text()='Enter the model & version of your product']/parent::header/following::input")
	private static WebElement TXTAREA_model;
	@FindBy(xpath = "//span[text()='How much did you pay for it?']/preceding::header/following-sibling::div//input")
	private static WebElement INPUT_amountPaid;
	@FindBy(xpath = "//input[@placeholder='dd/mm/yyyy']")
	private static WebElement INPUT_datePicker;
	@FindBy(xpath = "//p[text()='Was it brand new or was there a previous owner?']/following::input")
	private static WebElement SELECT_Condition;
	@FindBy(xpath = "//form/div//button[text()='Get quote']")
	private static WebElement BTN_getQuote;
	public WebDriver driver;


	public GetQuote(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public  void clickGetQuoteButton(){
		new WebDriverWait(driver, 600).until(
				driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
		BTN_getAQuote.click();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		Assert.assertTrue(TXT_productCategory.isDisplayed());
		//Assert.assertTrue(BTN_search.isDisplayed());

	}


	public  void selectCategory(){
		BTN_appliances.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", BTN_next);
		BTN_next.click();
		Assert.assertTrue(TXT_location.isDisplayed());

	}


	public  void enterAdress(){
		TXTAREA_fullAddress.sendKeys("92 Kurraba road");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		list_typeAheadAddress.click();
		BTN_next.click();
		Assert.assertTrue(LABEL_manufacturer.isDisplayed());

	}


	public  void enterBrand(){

		TXTAREA_manufacturer.sendKeys("SAMSUNG");
		BTN_next.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertTrue(LABEL_model.isDisplayed());

	}


	public  void enterModel(){
		
		TXTAREA_model.sendKeys("16");
		BTN_next.click();



	}
	public  void enterAmountPaid(){


		INPUT_amountPaid.sendKeys("100");
		BTN_next.click();

	}

	public  void enterDateBought(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		INPUT_datePicker.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement dateWidget = driver.findElement(By.xpath("//div[@data-visible=\"true\"]//table"));
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
		for (WebElement cell: columns){ 
			if (cell.getText().equals("24")){
				jse.executeScript("arguments[0].click();", cell);
				break; } }
		jse.executeScript("arguments[0].click();", BTN_next);

	}

	public  void enterCondition(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		SELECT_Condition.sendKeys("New");
		SELECT_Condition.sendKeys(Keys.ENTER); 
		jse.executeScript("arguments[0].click();", BTN_getQuote);		
	}



}
