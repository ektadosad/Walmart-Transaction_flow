/*
 * Filename: SignInPage.java
 *
 * Programmer: Ekta Dosad
 * Date:  11/19/2015
 */
package automationFramework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.MainTest;

/**
 * @author Ekta
 *
 */
public class SignInPage {
	
	/**
	 * Declaring Page Factory variables
	 */
	@FindBy(how = How.XPATH, using = ".//*[@id='login-username']")
	//@FindBy(how = How.ID, using = "login-username")
	static WebElement email;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='login-password']")
	//@FindBy(how = How.ID, using = "login-password")
	static WebElement password;
	
	@FindBy(how = How.XPATH, using = ".//form/div/button")
	static WebElement signIn;

	/**
	 * 
	 * Constructor
	 */
	public SignInPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * return WalmartHomeScreen
	 */
	public WalmartHomeScreen signIn() throws InterruptedException{
		email.sendKeys(MainTest.email);
		password.sendKeys(MainTest.password);
		signIn.click();
		WebElement myDynamicElement = (new WebDriverWait(MainTest.driver, 35)).
				until(ExpectedConditions.presenceOfElementLocated((By.xpath("//h1[contains(.,'Welcome to your Walmart account!')]"))));
		return new WalmartHomeScreen(MainTest.driver);
	}
	
}
