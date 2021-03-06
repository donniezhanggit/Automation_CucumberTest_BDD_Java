package Tests;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.*;

public class StepTestDescripton {

	WebDriver driver = null;
    
	//*
	//*
	// Scenario:verify that user is able to login using a valid username and password 
	// - Using a regular expression to call the Page more than a scenario
	//*
	//*
	
	@Given("^A user (?:is on|navigates to) (http.*)$")
	public void a_user_is_on_store_demoqa_com(String url) throws Throwable {
	  if(driver == null) { 
		driver = new ChromeDriver();
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(2,TimeUnit.MINUTES);
	  }
	   driver.get(url);
		
	}

	@When("^User Clicks on MyAccount link$")
	public void user_Clicks_on_MyAccount_link() throws Throwable {
		WebElement MyAccountLink = driver.findElement(By.className("account_icon"));
		MyAccountLink.click();
		
		Assert.assertNotNull("Impossible to find the Account Button", MyAccountLink);
	}

	@And("^User enters a valid username (.*) and password (.*)$")
	public void user_enters_a_valid_username_and_password(String userName, String password) throws Throwable {
	    
		WebElement UserName = driver.findElement(By.id("log"));
		UserName.clear();
		UserName.sendKeys(userName); // Type in username
		
		WebElement Password = driver.findElement(By.id("pwd"));
		Password.clear();
		Password.sendKeys(password); // Type in the password
		
		WebElement SubmitButton = driver.findElement(By.id("login"));
		SubmitButton.click(); //Click on LoginButton
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement validate = driver.findElement(By.className("display-name"));
		Assert.assertEquals("testecucumber",validate.getText());
	}

	@Then("^User is able to login sucessfully$")
	public void user_is_able_to_login_sucessfully() throws Throwable { 
	    
			WebElement logoutBtn = null;
		try
		    {
			logoutBtn = driver.findElement(By.id("account_logout"));
			logoutBtn.click();
			
		    driver.close();
		    }
		catch(Exception Exp) {
			System.out.println("Not able to find the element.");
			throw Exp;
		}
		
	      
	}

	//*
	//*
	//Scenario: login with a invalid username
	//*
	//*
	
	//@Given("^A user navigates to (.*)$")
	//public void a_User_is_on_the_login_page(String url) throws Throwable {
				   
	//}
	
	@And("^User enters a invalid username (.*) and valid password (.*)$")
	public void user_enters_a_invalid_username_and_valid_password(String userName,String password) throws Throwable {
		
		WebElement invalidUsername = driver.findElement(By.id("log"));
		//invalidUsername.sendKeys("");
		invalidUsername.clear();
		invalidUsername.sendKeys(userName); // Type in username
		
		WebElement Password = driver.findElement(By.id("pwd"));
		Password.clear();
		Password.sendKeys(password); // Type in the password
		
		WebElement SubmitButton = driver.findElement(By.id("login"));
		SubmitButton.click(); //Click on LoginButton
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Then("^Application should dine login to the user by displaying approprieate erro message$")
	public void application_should_dine_login_to_the_user_by_displaying_approprieate_erro_message() throws Throwable {
		
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(By.xpath(".//*[.='ERROR: Invalid username. Lost your password?']")).isDisplayed());
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.close();
		
	}
	
	    //*
	 	//*
		//Scenario: Login with invalid password
		//*
		//*
	
		@And("^User enters a valid username (.*) and invalid password (.*)$")
		public void user_enters_a_valid_username_and_invalid_password(String userName,String password) throws Throwable {
		   
			WebElement invalidUsername = driver.findElement(By.id("log"));
			//invalidUsername.sendKeys("");
			invalidUsername.clear();
			invalidUsername.sendKeys(userName); // Type in username
			
			WebElement Password = driver.findElement(By.id("pwd"));
			Password.clear();
			Password.sendKeys(password); // Type in the password
			
			WebElement SubmitButton = driver.findElement(By.id("login"));
			SubmitButton.click(); //Click on LoginButton
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		}

		@Then("^Application should dine login to the password by displaying approprieate erro message$")
		public void application_should_dine_login_to_the_password_by_displaying_approprieate_erro_message() throws Throwable {
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Assert.assertTrue(driver.findElement(By.xpath(".//*[.='ERROR: The password you entered for the username testecucumber is incorrect. Lost your password?']")).isDisplayed());
			
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.close();
		}

	

}
