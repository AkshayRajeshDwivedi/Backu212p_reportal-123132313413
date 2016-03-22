package org.reports.Generator.ReportsGenerator;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fgh;

public class ExportFromExcel {
	WebDriver driver;
	List<WebElement> users;
	List<String> name=new ArrayList<String>();
	
	
	   @BeforeClass(alwaysRun = true)
	   public void setup() throws IOException {
		   DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		   ChromeOptions options = new ChromeOptions();
		   Map<String, Object> prefs = new HashMap<String, Object>();
		   prefs.put("download.default_directory", TestConfig.downloaddirectory);
		   options.setExperimentalOption("prefs", prefs);
		   capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		   System.setProperty("webdriver.chrome.driver","C:\\Users\\Akshay Rajesh\\Desktop\\chromedriver.exe");
		   driver = new ChromeDriver(capabilities);
		   // String filePath = System.getProperty("user.dir")+"\\src\\excelExportAndFileIO";
		  //  ExtractExcel ext=new ExtractExcel();
		   // name= ext.readExcel(filePath,"ExportExcel.xlsx","");
		 //   name=ext.readExcel();

	   }
	  
	public void login()
	{
		driver.get("https://edatavisual.com/reportal/login.aspx?PortalId=251");
        driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Login 
		driver.findElement(By.id("UsernameText")).sendKeys("Testuser1");
		driver.findElement(By.id("__PasswordText")).sendKeys("Report@123");
		driver.findElement(By.id("LoginButton")).click();
	}
	@Test(priority=1)
	public void loginAdmin() throws InterruptedException
	 {
		
		int j=0,k=50;
		while(k<fgh.getName.size())
		{
			
		
	    for (int i=j;i<=k;i++)
	  	  {
	    
		   try
		   {
			   if(i==j)
			   {
				   login();
			   }
			   String dp=name.get(i);
				   driver.findElement(By.id("ctl10_drilldown")).click();
				   Thread.sleep(4000);
				   driver.findElement(By.xpath("/html/body/form/div[5]/div[2]/div/div/div/div[1]/div/input")).clear();
				   Thread.sleep(2000);
			       driver.findElement(By.xpath("/html/body/form/div[5]/div[2]/div/div/div/div[1]/div/input")).sendKeys(dp);
			       Thread.sleep(20000);
			       driver.findElement(By.xpath("/html/body/form/div[5]/div[2]/div/div/div/div[1]/div/input")).sendKeys(Keys.ENTER);
			       Thread.sleep(5000);
			       driver.findElement(By.linkText(dp)).click();
		           Thread.sleep(15000);
		           driver.findElement(By.xpath("//div[@class='dd-button-menu']/button")).click();
		           Thread.sleep(10000);
		           String script = "return document.getElementById('Nameforautomation').innerHTML";
		           String userName = (String) ((JavascriptExecutor) driver).executeScript(script);
			      String newName= userName.replace('"','_' );
		          System.out.println(newName);
		         driver.findElement(By.xpath("//*[@id='li-export']/a")).click();
		    	 WebElement frame_id=driver.findElement(By.id("innerContentFrame"));
		    	 driver.switchTo().frame(frame_id);
		    	 driver.findElement(By.xpath("//*[@id='exp_overriddenFileName_toggleOverride']")).click(); 
		    	 driver.findElement(By.xpath("//*[@id='exp_overriddenFileName_ibToggleMode']")).click(); 
			   	 driver.findElement(By.id("exp_overriddenFileName_tbFileName")).sendKeys(newName);
		   	     driver.switchTo().parentFrame();
		   	     driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[3]/span/button")).click();
		   	     driver.switchTo().frame(frame_id);
		   	     WebDriverWait wait = new WebDriverWait(driver, 1800);
		         wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("//*[@id='label_tasksStatus']/li"), "Completed"));
		       	 Thread.sleep(10000);
		   	     driver.switchTo().parentFrame(); 
		         driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[3]/span/button")).click();
		    	 Thread.sleep(15000);
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
			  
		   }
		   j=k+1;
		   k=k+50;
		   if((fgh.getName.size()-k)<0)
		   {
			   k=fgh.getName.size()-1;
		   }
		  
		   
		   }
	  	  }
	 }
     @AfterClass
	   public void driverQuit()
	   {
		   driver.close();
		   driver.quit();
   	   }
}
