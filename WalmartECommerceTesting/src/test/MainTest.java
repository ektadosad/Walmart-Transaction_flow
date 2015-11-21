/*
 * Filename: MainTest.java
 *
 * Programmer: Ekta Dosad
 * Date:  11/19/2015
 */
package test;

import static org.testng.AssertJUnit.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import automationFramework.Cart;
import automationFramework.ItemDescriptionPage;
import automationFramework.SearchResults;
import automationFramework.SignInPage;
import automationFramework.WalmartHome;
import automationFramework.WalmartHomeScreen;

/**
 * @author Ekta
 *
 */
public class MainTest {
	
	/**
	 * Declaring variables
	 */
	public static WebDriver driver = new FirefoxDriver();
	public static String email = "ekta.dosad@gmail.com";
	public static String password = "Test123";
	public static String[] itemList = {"tv", "socks", "dvd", "toys", "iPhone"};

	/**
	 * Creating page objects
	 */
	WalmartHome walmartHome = new WalmartHome(driver);
	SignInPage signInPage = new SignInPage(driver);
	WalmartHomeScreen homeScreen = new WalmartHomeScreen(driver);
	SearchResults searchResults = new SearchResults(driver);
	ItemDescriptionPage itemDescriptionPage = new ItemDescriptionPage(driver);
	Cart cart = new Cart(driver);
	
	/**
	 * Test case to launch the application  
	 */	
  @Test
  public void launchApplication() {
	  driver.navigate().to("http://www.walmart.com/");
	  driver.manage().window().maximize();
	  Assert.assertEquals(driver.getCurrentUrl(),"http://www.walmart.com/");
  }
	/**
	 * Test case to launch the application  
	 */	
  @Test
  public void navigateToSignInPage() {
	  signInPage = WalmartHome.launchingLogIn();
	  assertTrue(driver.getCurrentUrl().contains("login"));
  }
  
  /**
	 * Test case to Login using existing account
	 * @throws InterruptedException
	 */
  @Test(dependsOnMethods={"navigateToSignInPage"})
  public void logIn() throws InterruptedException{
	  homeScreen = signInPage.signIn();
	  assertTrue(driver.getCurrentUrl().contains("account"));
  }
  
  /**
	 * Test case to  Perform a search on home page from a pool of keywords
	 * @throws InterruptedException
	 */
  @Test(dependsOnMethods={"logIn"})
  public void search() throws InterruptedException{
	  searchResults = homeScreen.performSearch();
	  assertTrue(driver.getCurrentUrl().contains("search")); 
  }
  
  /**
	 * Test case to identify an item
	 * @throws InterruptedException
	 */
  @Test(dependsOnMethods={"search"})
  public void clickItem() throws InterruptedException{
	  itemDescriptionPage = searchResults.clickOnItem();
	  assertTrue(driver.getTitle().contains("Walmart.com"));
  }
  
  /**
	 * Test case to add the item to cart
	 * @throws InterruptedException
	 */
  @Test(dependsOnMethods={"clickItem"})
  public void addToCart() throws InterruptedException{
	  cart = itemDescriptionPage.addItemToCart();
	  assertTrue(driver.getCurrentUrl().contains("cart")); 
  }
  
  /**
	 * Test case to validate the item in cart
 * @throws Exception 
	 */
@Test(dependsOnMethods={"addToCart"})
public void validateCart() throws Exception{
	  cart.validateCart();
	  assertTrue(driver.getCurrentUrl().contains("cart")); 
}

/**
 * Test case to click on Sign Out
 */
@Test(dependsOnMethods={"validateCart"})
public void signOut() {
	walmartHome = cart.logOut();
	assertTrue(driver.getCurrentUrl().contains("logout"));
}

/**
 * Test case to close the browser
 */
@Test(dependsOnMethods={"signOut"})
public void shutTheBrowser() {
	driver.quit();
	assertTrue(driver.toString().contains("null"));
}
}

