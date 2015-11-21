/*
 * Filename: Cart.java
 *
 * Programmer: Ekta Dosad
 * Date:  11/19/2015
 */
package automationFramework;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.MainTest;

/**
 * @author Ekta
 *
 */
public class Cart {
	
	/**
	 * Declaring Page Factory variables
	 */
	@FindBy(how = How.XPATH, using = ".//*[@id='spa-layout']/div/div/div[1]/div/h3/span")
	static WebElement noOfItems;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='spa-layout']/div/div/div[1]/div/div[5]/div[1]/div[1]/p[1]/small")
	static WebElement itemQuantity;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[2]/section/section[4]/div/div/div[1]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/div[3]/div[1]/a/span")
	static WebElement itemNameOnCart; 
	
	@FindBy(how = How.XPATH, using = ".//*[@id='spa-layout']/div/div/div[1]/div/div[4]/div[2]/div[1]/div/div[4]/div/div[1]/div/div/div/select")
	static WebElement quantity;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='top']/div[3]/div/div/div/div/div[4]/div/div[1]/div[1]/div/a")
	static WebElement myAccount;
	/**
	 * Declaring variables
	 */
	int expectedQuantity = ItemDescriptionPage.selectedValue;
	static String actualName;

	/**
	 * 
	 * Constructor
	 */
	public Cart(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Test case to validate the item in cart
	 */
	public void validateCart() throws Exception{
		String[] splited = noOfItems.getText().split(" ");
		String[] splited1 = itemQuantity.getText().substring(1, itemQuantity.getText().length()-1).split(" ");
		Select itemList = new Select(quantity);
		int actualQuantity =  Integer.parseInt(itemList.getFirstSelectedOption().getText());
		actualName = itemNameOnCart.getText();
		Assert.assertEquals(ItemDescriptionPage.selectedItmeName, actualName);
		Assert.assertEquals(expectedQuantity, actualQuantity);
		Assert.assertTrue(expectedQuantity == Integer.parseInt(splited[0])  &&  expectedQuantity == Integer.parseInt(splited1[0]));
	}
	
	/*
	 * Test case to log Out
	 */
	public WalmartHome logOut(){
		Actions action = new Actions(MainTest.driver);
		action.moveToElement(myAccount);
		MainTest.driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		WebElement signOut = MainTest.driver.findElement(By.xpath(".//*[@id='flyout15']/ul/span[1]/li[6]/a"));
		action.moveToElement(signOut);
		action.click();
		action.perform();
		return new WalmartHome(MainTest.driver);
	}

}
