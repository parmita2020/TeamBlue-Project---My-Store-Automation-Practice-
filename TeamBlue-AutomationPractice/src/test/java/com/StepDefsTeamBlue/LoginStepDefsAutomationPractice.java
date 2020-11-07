package com.StepDefsTeamBlue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.PageFactoryTeamBlue.LoginPageAutomationPractice;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefsAutomationPractice {

	WebDriver driver;
	ArrayList<String> PriceList;
	String SecondPrice;
	LoginPageAutomationPractice object;

	@Given("^user open web browser and navigate to MyStore login screen$")
	public void user_open_web_browser_and_navigate_to_MyStore_login_screen() throws Throwable {

		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();
		object = PageFactory.initElements(driver, LoginPageAutomationPractice.class);

	}

	@Then("^user navigate to home page and verify the page title \"([^\"]*)\"$")
	public void user_navigate_to_home_page_and_verify_the_page_title(String title) throws Throwable {
		System.out.println(driver.getTitle());

		String actualTitle = driver.getTitle().trim();
		Assert.assertTrue("Page title does not match", actualTitle.equals(title));

	}

	@Then("^user click the Sign in button on the right$")
	public void user_click_the_Sign_in_button_on_the_right() throws Throwable {

		WebElement signin = driver.findElement(By.xpath("//*[@title='Log in to your customer account']"));
		signin.click();
	}

	@Then("^user enter a valid username and password$")
	public void user_enter_a_valid_username_and_password() throws Throwable {

		object.getUsername().sendKeys("parmita.roy@outlook.com");
		object.getPassword().sendKeys("Smarttech2020*");
	}

	@Then("^user click the Sign in button$")
	public void user_click_the_Sign_in_button() throws Throwable {

		WebElement signin = driver.findElement(By.xpath("//*[@class='icon-lock left']"));
		signin.click();

	}

	@Then("^user click on the upper left corner Dresses link and display should show (\\d+) dresses$")
	public void user_click_on_the_upper_left_corner_Dresses_link_and_display_should_show_dresses(int arg1)
			throws Throwable {

		driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[2]/a")).click();
	}

	@Then("^user print all the dress prices in descending order on the console$")
	public void user_print_all_the_dress_prices_in_descending_order_on_the_console() throws Throwable {

//		Scroll down the page
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1200)");

//		Highlight any object
//		WebElement abc = driver.findElement(By.xpath("//*[@id =\"ul_layered_quantity_0\"]/li/label/a"));
//		jse.executeScript("arguments[0].setAttribute('style','background:yellow; border:2px solid red;');", abc);	
//		Thread.sleep(10000);

		List<WebElement> DressPrices = driver
				.findElements(By.xpath("//*[@class='product-desc']/following-sibling::div[1]"));

		PriceList = new ArrayList<String>();

		for (int i = 0; i < DressPrices.size(); i++) {
			PriceList.add(DressPrices.get(i).getText().toString());
		}
//		Printing in descending order
		Collections.sort(PriceList, Collections.reverseOrder());

		System.out.println("Dress Prices in Descending Order:" + PriceList);
	}

	@Then("^user select the second dress on the current list and remember the price$")
	public void user_select_the_second_dress_on_the_current_list_and_remember_the_price() throws Throwable {

		SecondPrice = PriceList.get(1);
		System.out.println("Price of the second dress: " + SecondPrice);

		driver.findElement(By.xpath("(//*[contains(text(),'" + SecondPrice + "')])[2]")).click();
		Thread.sleep(3000);

	}

	@Then("^user click on next page Proceed to checkout$")
	public void user_click_on_next_page_Proceed_to_checkout() throws Throwable {
//		Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement CheckOutButton =  driver.findElement(By.xpath("//*[contains(text(),'Proceed to checkout')]"));
		wait.until(ExpectedConditions.visibilityOf(CheckOutButton)).click();
	}

	@When("^user click on the next page to verify the total price with shipping$")
	public void user_click_on_the_next_page_to_verify_the_total_price_with_shipping() throws Throwable {

		Double ExpectedShippingPrice = Double.parseDouble(SecondPrice.replace("$", "")) + 2;
		System.out.println("The total price with shipping is: " + ExpectedShippingPrice);
		Double ActualShipingPrice = Double
				.parseDouble(driver.findElement(By.xpath("//*[@id='total_price']")).getText().replace("$", ""));

		Assert.assertTrue(ActualShipingPrice.equals(ExpectedShippingPrice));
	}

	@Then("^user click on sign out button for MyStore and close the window$")
	public void user_click_on_sign_out_button_for_MyStore_and_close_the_window() throws Throwable {

		WebElement logout = driver.findElement(By.xpath("//*[@class='logout']"));

//		ExplicitWait

		// logout.click();
//		WebDriverWait wait = new WebDriverWait(driver,10);
//		wait.until(ExpectedConditions.visibilityOf(logout)).click();

//		ImplicitWait

//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		logout.click();

//		FluentWait

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(1,
				TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(logout)).click();

//		Close the window
		driver.close();
	}

}
