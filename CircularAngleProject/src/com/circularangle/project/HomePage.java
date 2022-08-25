package com.circularangle.project;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class HomePage 
{
	WebDriver driver;
	WebElement element;
	
		@BeforeTest
		public void StartApp()
		{
			System.setProperty("webdriver.chrome.driver","D:\\automation\\Library Files\\chromedriver.exe");
			driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.navigate().to("http://ecluepro.com/");
			String currentUrl=driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("pro"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		
		@Test
		public void LoginSucess()
		{
			driver.findElement(By.id("txtUserName")).sendKeys("10001207");
			driver.findElement(By.id("txtPassWord")).sendKeys("123");
			driver.findElement(By.id("btnLogin")).click();
			WebDriverWait wait= new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(".//*[@id='carousel']/div/ul/li[1]/a/div/div/i/img"))));
			boolean status= driver.findElement(By.xpath(".//*[@id='carousel']/div/ul/li[1]/a/div/div/i/img")).isDisplayed();
			Assert.assertTrue(status);
		}
		
		//navigating through all screen and travelling to Journey window
		@Test(priority=2)
		public void EmployeeSelfService() throws InterruptedException
		{
			driver.findElement(By.xpath(".//*[@id='carousel']/div/ul/li[1]/a/div/div/i/img")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Actions act= new Actions(driver);
			element=driver.findElement(By.xpath(".//*[@id='mnu_21']"));
			act.moveToElement(element).build().perform();
			driver.findElement(By.xpath(".//*[@id='mnu_29']")).click();
			driver.findElement(By.xpath(".//*[@id='MainContent_txtContactNo']")).sendKeys("634675346857");
			driver.findElement(By.xpath(".//*[@id='MainContent_btnAddEmp']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			element= driver.findElement(By.cssSelector("#popup_ok"));
			element.click();
			driver.findElement(By.xpath(".//*[@id='MainContent_grdEmpDetails_lnkBtnAddJourney_0']")).click();
			Thread.sleep(2000L);
			
		}
		
		//when From date is back dated
		@Test(priority=3,enabled=false)
		public void Journey() throws InterruptedException, ParseException
		{
			try
			{

				//selected domestic from flights selections
				driver.findElement(By.xpath(".//*[@id='MainContent_rbTravelType_1']")).click();
				Thread.sleep(2000L);
				 WebElement customdate1= driver.findElement(By.name("ctl00$MainContent$txtFromDate"));
				 customdate1.sendKeys("21-09-2017");
				 driver.findElement(By.xpath(".//*[@id='MainContent_txtFromTime']")).sendKeys("03:00");
				 //to get the date from the text field
				 String userdate=customdate1.getAttribute("value");
				
			        //to enter the user date 
			        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			        Date Userinputdate = sdf.parse(userdate);
			        System.out.println("User entered date is "+sdf.format(Userinputdate));
				 
				 //to get the system current date
			        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			        Date Currentsystemdate = new Date();
			        System.out.println("System date is "+dateFormat.format(Currentsystemdate));
					 WebElement todate= driver.findElement(By.name("ctl00$MainContent$txtToDate"));
					 todate.sendKeys("30-09-2017");
					 driver.findElement(By.xpath(".//*[@id='MainContent_txtToTime']")).sendKeys("22:00");
					 
					 //getting the from location from the dropdown
					 WebElement frmlcn=driver.findElement(By.xpath(".//*[@id='s2id_MainContent_drpFromLocation']/a/span[2]/b"));
					 frmlcn.click();
					 Thread.sleep(1000L);
					 driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("Delhi");
					 driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
					 WebElement tolcn=driver.findElement(By.xpath(".//*[@id='s2id_MainContent_drpToLocation']/a/span[2]/b"));
					 tolcn.click();
					 driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("Chen");
					 driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
					 Thread.sleep(1000L);
					 
					 //mode of travel
					 WebElement mode=driver.findElement(By.xpath(".//*[@id='s2id_MainContent_drpModeOfTravel']/a/span[2]/b"));
					 mode.click();
					 driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("aut");
					 driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
					 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					 
					 //purpose of travel
					 driver.findElement(By.xpath(".//*[@id='MainContent_txtPurposeOfTravel']")).sendKeys("Client side visit");
		         	Assert.assertFalse(Math.abs(Currentsystemdate.getTime() - Userinputdate.getTime()) <=0);
		         	Thread.sleep(2000L);
		         	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				driver.close();
			}
			
		}
		
		//When From date is not entered by the user
		@Test(priority=4)
		public void datevalidation() throws InterruptedException, ParseException
		{
					//selected domestic from flights selections
					driver.findElement(By.xpath(".//*[@id='MainContent_rbTravelType_1']")).click();
					Thread.sleep(2000L);
					 WebElement todate= driver.findElement(By.name("ctl00$MainContent$txtToDate"));
					 todate.sendKeys("30-09-2017");
					 driver.findElement(By.xpath(".//*[@id='MainContent_txtToTime']")).sendKeys("22:00");
					 
					 //getting the from location from the dropdown
					 WebElement frmlcn=driver.findElement(By.xpath(".//*[@id='s2id_MainContent_drpFromLocation']/a/span[2]/b"));
					 frmlcn.click();
					 Thread.sleep(1000L);
					 driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("Delhi");
					 driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
					 WebElement tolcn=driver.findElement(By.xpath(".//*[@id='s2id_MainContent_drpToLocation']/a/span[2]/b"));
					 tolcn.click();
					 driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("Chen");
					 driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
					 Thread.sleep(1000L);
					 
					 //mode of travel
					 WebElement mode=driver.findElement(By.xpath(".//*[@id='s2id_MainContent_drpModeOfTravel']/a/span[2]/b"));
					 mode.click();
					 driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("aut");
					 driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
					 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					 
					 //purpose of travel
					 driver.findElement(By.xpath(".//*[@id='MainContent_txtPurposeOfTravel']")).sendKeys("Client side visit");
					 driver.findElement(By.xpath(".//*[@id='MainContent_btnSaveJourney']")).click();	
					 WebElement txt=driver.findElement(By.xpath(".//*[@id='popup_message']"));
		         	 String alerttxt=txt.getText();
		         	 if(alerttxt.equals("Please select from date"))
		         	 {
		         		 System.out.println("From date is not entered, please enter the from date first");
		         	 }
		         	 Thread.sleep(2000L);
		         	driver.findElement(By.xpath(".//*[@id='popup_ok']")).click();
		}
		
				//creation of new guest with pass validation
				@Test(priority=5)
				public void guestcreation() throws InterruptedException
				{
					driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					driver.findElement(By.xpath(".//*[@id='MainContent_btnDiscard']")).click();
					Thread.sleep(3000L);
					WebElement myactivityelement=driver.findElement(By.xpath(".//*[@id='mnu_21']"));
					Actions acts= new Actions(driver);
					acts.moveToElement(myactivityelement).build().perform();
					driver.findElement(By.xpath(".//*[@id='mnu_29']")).click();
					
					//selecting whether the Travel by person is a user or a guest
					driver.findElement(By.xpath(".//*[@id='s2id_MainContent_drpTravelBy']/a/span[2]/b")).click();
					driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("Gue");
					Thread.sleep(1000L);
					driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
					Thread.sleep(1000L);
					//Name of the guest
					driver.findElement(By.xpath(".//*[@id='MainContent_txtEmpName']")).sendKeys("ABC");
					Thread.sleep(1000L);
					//selecting the mode of gender for that particular guest
					driver.findElement(By.xpath(".//*[@id='s2id_MainContent_ddlGender']/a/span[2]/b")).click();
					driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("Fem");
					driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
					
					//entering the contact number of Guest user
					driver.findElement(By.xpath(".//*[@id='MainContent_txtContactNo']")).sendKeys("9819819812");
					
					//age
					driver.findElement(By.xpath(".//*[@id='MainContent_txtAge']")).sendKeys("26");
					//adding new contents of the guest and clicking on add
					driver.findElement(By.xpath(".//*[@id='MainContent_btnAddEmp']")).click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					element= driver.findElement(By.cssSelector("#popup_ok"));
					element.click();
					Thread.sleep(2000L);
					driver.findElement(By.xpath(".//*[@id='MainContent_grdEmpDetails_lnkBtnAddJourney_1']")).click();
					driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					driver.findElement(By.xpath(".//*[@id='MainContent_rbTravelType_1']")).click();
			        Thread.sleep(3000L);
			        //from date and time is entered
			        driver.findElement(By.name("ctl00$MainContent$txtFromDate")).sendKeys("11-10-2017");
			        driver.findElement(By.name("ctl00$MainContent$txtFromTime")).sendKeys("06:00");
			        Thread.sleep(2000L);
			        //to date and time is entered
			        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			        driver.findElement(By.name("ctl00$MainContent$txtToDate")).sendKeys("17-10-2017");
			        driver.findElement(By.name("ctl00$MainContent$txtToTime")).sendKeys("22:00");
			        Thread.sleep(2000L);
			        //from as well as to location is entered
			        driver.findElement(By.xpath(".//*[@id='s2id_MainContent_drpFromLocation']/a/span[2]/b")).click();
			        Thread.sleep(2000L);
			        
			        driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("Del");
			        driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
			        Thread.sleep(2000L);
			        
			        driver.findElement(By.xpath(".//*[@id='s2id_MainContent_drpToLocation']/a/span[2]/b")).click();
			        Thread.sleep(2000L);
			        driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("Chenn");
			        driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
			        
			        //mode of travel
			        driver.findElement(By.xpath(".//*[@id='s2id_MainContent_drpModeOfTravel']/a/span[2]/b")).click();
			        Thread.sleep(2000L);
			        driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("Bus");
			        driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
			        
			        driver.findElement(By.xpath(".//*[@id='MainContent_txtPurposeOfTravel']")).sendKeys("Full on final");
			        driver.findElement(By.xpath(".//*[@id='MainContent_btnSaveJourney']")).click();
			        Thread.sleep(2000L);
			        driver.findElement(By.xpath(".//*[@id='popup_ok']")).click();
				}
				
				//when To date is less than From date
				@Test(priority=6)
				public void datevalidation1() throws InterruptedException
				{
				
		        Actions act= new Actions(driver);
		        WebElement hover= driver.findElement(By.xpath(".//*[@id='mnu_21']"));
		        act.moveToElement(hover).build().perform();
		        Thread.sleep(3000L);
		        driver.findElement(By.xpath(".//*[@id='mnu_29']")).click();
		        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		        
		        driver.findElement(By.xpath(".//*[@id='s2id_MainContent_drpTravelBy']/a/span[2]/b")).click();
				driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("Gue");
				Thread.sleep(1000L);
				driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
				Thread.sleep(1000L);
				//Name of the guest
				driver.findElement(By.xpath(".//*[@id='MainContent_txtEmpName']")).sendKeys("MCA");
				Thread.sleep(1000L);
				//selecting the mode of gender for that particular guest
				driver.findElement(By.xpath(".//*[@id='s2id_MainContent_ddlGender']/a/span[2]/b")).click();
				driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("Ma");
				driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
				
				//entering the contact number of Guest user
				driver.findElement(By.xpath(".//*[@id='MainContent_txtContactNo']")).sendKeys("7776665552");
				
				//age
				driver.findElement(By.xpath(".//*[@id='MainContent_txtAge']")).sendKeys("30");
				//adding new contents of the guest and clicking on add
				driver.findElement(By.xpath(".//*[@id='MainContent_btnAddEmp']")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				element= driver.findElement(By.cssSelector("#popup_ok"));
				element.click();
				Thread.sleep(2000L);
				driver.findElement(By.xpath(".//*[@id='MainContent_grdEmpDetails_lnkBtnAddJourney_3']")).click();
				
		        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		        //travel type is selected
		        driver.findElement(By.xpath(".//*[@id='MainContent_rbTravelType_1']")).click();
		        Thread.sleep(3000L);
		        //from date and time is entered
		        driver.findElement(By.name("ctl00$MainContent$txtFromDate")).sendKeys("11-10-2017");
		        driver.findElement(By.name("ctl00$MainContent$txtFromTime")).sendKeys("21:00");
		        Thread.sleep(2000L);
		        //to date and time is entered
		        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		        driver.findElement(By.name("ctl00$MainContent$txtToDate")).sendKeys("15-09-2017");
		        driver.findElement(By.name("ctl00$MainContent$txtToTime")).sendKeys("22:00");
		        Thread.sleep(2000L);
		        //from as well as to location is entered
		        driver.findElement(By.xpath(".//*[@id='s2id_MainContent_drpFromLocation']/a/span[2]/b")).click();
		        Thread.sleep(2000L);
		        
		        driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("Pune");
		        driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		        Thread.sleep(2000L);
		        
		        driver.findElement(By.xpath(".//*[@id='s2id_MainContent_drpToLocation']/a/span[2]/b")).click();
		        Thread.sleep(2000L);
		        driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("Mum");
		        driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		        
		        //mode of travel
		        driver.findElement(By.xpath(".//*[@id='s2id_MainContent_drpModeOfTravel']/a/span[2]/b")).click();
		        Thread.sleep(2000L);
		        driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys("Auto");
		        driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		        
		        driver.findElement(By.xpath(".//*[@id='MainContent_txtPurposeOfTravel']")).sendKeys("testing only");
		        driver.findElement(By.xpath(".//*[@id='MainContent_btnSaveJourney']")).click();
				}
}
