/*
 * Filename: WalmartHome.java
 *
 * Programmer: Ekta Dosad
 * Date:  11/19/2015
 */
package automationFramework;

import java.util.concurrent.TimeUnit;

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
public class WalmartHome {
	
	/**
	 * Declaring Page Factory variables
	 */
	@FindBy(how = How.LINK_TEXT, using = "Sign In")
	static WebElement signIn;
	
	/**
	 * 
	 * Constructor
	 */
	public WalmartHome(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * @return SignInPage
	 */
	public static SignInPage launchingLogIn() {
		signIn.click();
		WebElement myDynamicElement = (new WebDriverWait(MainTest.driver, 35)).
				until(ExpectedConditions.presenceOfElementLocated((By.xpath("//h1[contains(.,'Sign in to your account')]"))));
		return new SignInPage(MainTest.driver);
	}
}
