package Automation_Test.Selenium_Java;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Initialize_Browser  {

	public String baseUrl = "https://github.com/";
	String driverPath = "C:\\SeleniumAutomation\\SeleniumDrivers\\chromedriver.exe";
	public static WebDriver driver = null;

	@BeforeSuite
	public void launchBrowser() throws InterruptedException  {

	       System.out.println("launching chrome browser");
	       System.setProperty("webdriver.chrome.driver", driverPath);
	       driver = new ChromeDriver();
	       driver.get(baseUrl);
	       driver.manage().window().maximize();
	       Thread.sleep(2000);
	}


	@Test
	public void signIN() throws InterruptedException {

	      String username = null, password ;

	       Scanner in = new Scanner(System.in);
	System.out.println("Enter a username: "); username = in.nextLine();
	       System.out.println("Enter a password: "); password = in.nextLine();
	 //     driver.findElement(By.xpath("//a[@class='HeaderMenu-link no-underline mr-3']")).click();
	       WebElement email = driver.findElement(By.id("login_field"));
	       WebElement passwd = driver.findElement(By.id("password"));
	       WebElement sign_in = driver.findElement(By.name("commit"));
	       Thread.sleep(1000);
	       email.sendKeys(username);
	       passwd.sendKeys(password);
	       sign_in.click();
	       
	       Thread.sleep(2000);


	       
	       //Create Repository
	driver.findElement(By.xpath("//*[@class='octicon octicon-plus']")).click();
	       driver.findElement(By.xpath("//*[@class='dropdown-item']")).click();
	       String repo_name ;
	                    
	                    System.out.println("Enter a repository name : "); 
	                    repo_name = in.nextLine();
	       
	       driver.findElement(By.id("repository_name")).sendKeys(repo_name);
	       driver.findElement(By.id("repository_description")).sendKeys("Test_description");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	       js.executeScript("window.scrollBy(0,150)");
	       Thread.sleep(2000);
	driver.findElement(By.xpath("//button[@class='btn btn-primary first-in-line']")).click();

	System.out.println("Repository created successfully");
	       
	 
	 //Create Issue
	String issue_name = "test_issue";
	//Click on Issues Link
	 driver.findElement(By.xpath("//span[contains(text(),'Issues')]")).click();
	 //Click on New Issue Button
	 driver.findElement(By.xpath("//span[@class='d-none d-md-block']")).click();
	 //Enter Issue Title
	 driver.findElement(By.xpath("//input[@id='issue_title']")).sendKeys(issue_name);
	 js.executeScript("window.scrollBy(0,150)");
	 //submit issue create button
	 driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
	 System.out.println("issue created successfully");
	 
	 
	 //Attach above issue name and description in new issue
	String previousIssue_id = driver.findElement(By.xpath("//span[@class='gh-header-number']")).getText();
	//System.out.println("previous ID=" +previousIssue_id);
	//Click on New Issue Button
	driver.findElement(By.xpath("//a[@class='btn btn-sm btn-primary m-0 ml-2 ml-md-2']")).click();
	driver.findElement(By.xpath("//input[@id='issue_title']")).sendKeys("New issue Raised - Prev Issue ID is : "+previousIssue_id);
	js.executeScript("window.scrollBy(0,150)");
	//submit issue create button
	driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
	System.out.println("New issue created successfully with first issue link mentioned");







	//Add Comment to issue created above
	//Comment Field
	driver.findElement(By.xpath("//textarea[@id='new_comment_field']")).sendKeys("Comment Added to the issue");
	js.executeScript("window.scrollBy(0,100)");
	//COMMENT submit button
	driver.findElement(By.xpath("//button[contains(text(),'Comment')]")).click();
	//Add Emoji to Repo created above
	//To be done********

	Thread.sleep(2000);


	driver.findElement(By.xpath("//textarea[@id='new_comment_field']")).click();
	driver.findElement(By.xpath("//textarea[@id='new_comment_field']")).sendKeys(previousIssue_id);
	// System.out.println("previous ID=" +previousIssue_id);
	//  js.executeScript("window.scrollBy(0,150)");
	//COMMENT submit button
	driver.findElement(By.xpath("//button[contains(text(),'Comment')]")).click();
	// System.out.println("Add Comment with above issue link and click on that issue link from comment");

	Thread.sleep(2000);

	driver.findElement(By.xpath("//a[@class='issue-link js-issue-link']")).click();



	//Delete Repository

	//click settings

	Thread.sleep(2000);
	driver.findElementByLinkText(repo_name).click();
	//  driver.findElement(By.xpath("//a[contains(text(),repo_name)]")).click();

	Thread.sleep(2000);
	driver.findElement(By.xpath("//span[contains(text(),'Settings')]")).click();
	//scroll down


	Thread.sleep(2000);
	js.executeScript("window.scrollBy(0,2000)");

	//click on delete this repository


	WebElement delete = driver.findElement(By.xpath("//summary[contains(text(),'Delete this repository')]"));

	js.executeScript("arguments[0].scrollIntoView();", delete);
	delete.click();

	Thread.sleep(2000);

	driver.findElementByXPath("//input[@aria-label='Type in the name of the repository to confirm that you want to delete this repository.']").sendKeys(username+'/'+repo_name);

	driver.findElement(By.xpath("//button[contains(text(),'I understand the consequences, delete this reposit')]")).click();

	// driver.switchTo().activeElement();

	//  Thread.sleep(2000);
	// driver.findElementByXPath("//input[@class='form-control input-block' and @name='verify']").sendKeys("hello");




	// driver.findElementByXPath("//input[@class='form-control input-block']").sendKeys("hello");
	              
	//driver.switchTo().activeElement();
	//  driver.switchTo().alert();
	//   new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='form-control input-block' and @aria-label,'Type in the name of the repository to confirm that you want to delete this repository']")));

	//  driver.findElementByXPath("//input[conatins(@class='form-control input-block' and @aria-label,'Type in the name of the repository to confirm that you want to delete this repository']").sendKeys("hello");

	// driver.findElementByCssSelector("[aria-label=Type in the name of the repository to confirm that you want to delete this repository]");

	//   driver.findElement(By.xpath("//details-dialog[@aria-label='Delete repository']/child::div[3]//child::form[1]/child::p/child::input")).sendKeys("hello");

	/*   try{
	Alert alert= driver.switchTo().alert();

	alert.sendKeys("hello");

	}

	catch(NoAlertPresentException e)
	{
	   System.out.println("No alert is present. Please check.");
	}
	*/


	//   driver.findElementByName("verify").sendKeys("Hello"); 
	// Alert alert= driver.switchTo().alert(); 

	//driver.findElementByXPath("//input[@class='form-control input-block']").sendKeys("hello");



	//switch to alert and enter repo name
	//   driver.findElementByName("verify").sendKeys("Hello");

	//  WebElement ele = driver.findElementByXPath("//div[text()='Are you absolutely sure?']");

	//System.out.println(ele);


	//  Thread.sleep(2000);
	//   driver.findElementByClassName("form-control input-block").sendKeys(username+ "/" +repo_name);
	//  driver.findElementByClassName("form-control input-block").sendKeys("Hello");

	//  driver.findElement(By.xpath("//button[contains(text(),'I understand the consequences, delete this reposit')]")).click();



	}




	}
