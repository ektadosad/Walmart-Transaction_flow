/*
 * Filename: ItemDescriptionPage.java
 *
 * Programmer: Ekta Dosad
 * Date:  11/19/2015
 */
package automationFramework;

import static org.testng.AssertJUnit.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
public class ItemDescriptionPage {
	
	/**
	 * Declaring Page Factory variables
	 */
	@FindBy(how = How.XPATH, using = "//*[contains(text(), 'Add to Cart')]")
	static WebElement button;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='PACViewCartBtn']")
	static WebElement viewCartButton;
		
    @FindBy(how = How.XPATH, using = "html/body/div[2]/section/section[4]/div/div[2]/div[1]/div[4]/div/h1/span")
	static WebElement itemName;		  

	@FindBy(how = How.XPATH, using = ".//*[@id='WMItemQtyDropDown']")
	static WebElement itemQuantity;
	
	/**
	 * Declaring variables
	 */
	static int selectedValue;
	static String selectedItmeName;
	
	/**
	 * 
	 * Constructor
	 */
	public ItemDescriptionPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/*
	 * return Cart
	 */
	public Cart addItemToCart() throws InterruptedException{
		
		selectedItmeName = itemName.getText();
		Select quantityList = new Select(itemQuantity);
		List<WebElement> options = quantityList.getOptions();
		Random rand  = new Random();
		int index = rand.nextInt(options.size()-1);
		quantityList.selectByValue(options.get(index).getText());
		selectedValue = Integer.parseInt(quantityList.getFirstSelectedOption().getText());	
		if(button.isEnabled()){
		//scroll until the element is in view
		((JavascriptExecutor) MainTest.driver).executeScript("arguments[0].scrollIntoView(true);", button);
		//Thread.sleep(500);
		button.click();
		WebElement myDynamicElement = (new WebDriverWait(MainTest.driver, 35)).
				until(ExpectedConditions.presenceOfElementLocated((By.xpath(".//*[@id='PACViewCartBtn']"))));
		
		viewCartButton.click();
		WebElement dynamicElement = (new WebDriverWait(MainTest.driver, 35)).
				until(ExpectedConditions.presenceOfElementLocated((By.xpath(".//*[@id='CartChkOutBtn']"))));
		}
		return new Cart(MainTest.driver);
	}
}
