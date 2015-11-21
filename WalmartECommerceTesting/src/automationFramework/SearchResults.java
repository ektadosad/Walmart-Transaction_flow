/*
 * Filename: SearchResults.java
 *
 * Programmer: Ekta Dosad
 * Date:  11/19/2015
 */
package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class SearchResults {
	
	/**
	 * Declaring Page Factory variables
	 */
	@FindBy(how = How.XPATH, using = ".//*[@id='tile-container']/div[1]/a")
	static WebElement tv;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='tile-container']/ul/li[3]/div/a[1]") 
	static WebElement socks;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='tile-container']/div[2]/div/div/h4/a")
	static WebElement dvd;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='sponsored-container-middle-2']/div/div[2]/ol/div/div/li[1]/div/div[2]/a/span")
	static WebElement toys;			  
	
	@FindBy(how = How.XPATH, using = ".//*[@id='tile-container']/div[1]/div/div/h4/a")
	static WebElement iPhone;
	
	/**
	 * Declaring variables
	 */
	static String title;
	
	/**
	 * 
	 * Constructor
	 */
	public SearchResults(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * return selected item
	 */
	public WebElement selectItemToAdd(){
		title  = MainTest.driver.getTitle();
		switch(title){
		case "tv - Walmart.com":	
			return tv;
		case"socks - Walmart.com":
			return socks;
		case "dvd - Walmart.com":
			return dvd;
		case "Toys - Walmart.com":
			return toys;
		case "iphone - Walmart.com":
			return iPhone;
		}
		return null;	
	}
	
	/*
	 * return ItemDescriptionPage
	 */
	public ItemDescriptionPage clickOnItem() throws InterruptedException{
		WebElement item = selectItemToAdd();
		//scroll until the element is in view
		((JavascriptExecutor) MainTest.driver).executeScript("arguments[0].scrollIntoView(true);", item);
		Thread.sleep(500);
		item.click();
		WebElement myDynamicElement = (new WebDriverWait(MainTest.driver, 35)).
				until(ExpectedConditions.presenceOfElementLocated((By.xpath("//*[contains(text(), 'Add to Cart')]"))));
		return new ItemDescriptionPage(MainTest.driver);
		
	}

}
