/*
 * Filename: WalmartHomeScreen.java
 *
 * Programmer: Ekta Dosad
 * Date:  11/19/2015
 */
package automationFramework;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.MainTest;

/**
 * @author Ekta
 *
 */
public class WalmartHomeScreen {
	
	/**
	 * Declaring Page Factory variables
	 */
	@FindBy(how = How.ID, using = "search")
	static WebElement searchitem;

	// .//*[@id='top']/div[3]/div/div/div/div/div[3]/form/div/div[3]/button
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'searchbar-submit js-searchbar-submit')]")
	static WebElement searchButton; 
	@FindBy(how = How.XPATH, using = "html/body/div[2]/section/section[4]/div/div/div/div/div[2]/div/h1")
	static WebElement textelem;
	
	/**
	 * 
	 * Constructor
	 */
	public WalmartHomeScreen(WebDriver driver){
		PageFactory.initElements(driver, this);	
	}
	
	/*
	 * return item
	 */
	public String selectItem(){
		Random rand  = new Random();
		int index = rand.nextInt(MainTest.itemList.length-1);
		return MainTest.itemList[index];
	}
	
	/*
	 * return SearchResults
	 */
	public SearchResults performSearch() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(MainTest.driver, 20);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(textelem));
		String item = selectItem();
		searchitem.sendKeys(item);
		searchButton.click();
		WebElement myDynamicElement = (new WebDriverWait(MainTest.driver, 35)).
				until(ExpectedConditions.presenceOfElementLocated((By.id("search"))));
		return new SearchResults(MainTest.driver);
	}
}
