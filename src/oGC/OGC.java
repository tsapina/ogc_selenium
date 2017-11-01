package oGC;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class OGC extends parentOGC {

	public Select country;
	public Select month;
	public Select day;
	public Select year;
	public JavascriptExecutor js;
	public WebElement From;
	public WebElement To;
	public Actions builder;
	public Action dragAndDrop;
	
	@Test
	public void test() 
	{
		logger.log(LogStatus.INFO, "http://demoqa.com/ opened");
		
		//otvoriti stranicu http://demoqa.com/
		driver.get("http://demoqa.com/");
		
		//na desnoj strani kliknuti plavi gumb Registration
		driver.findElement(By.partialLinkText("Registration")).click();
		
		logger.log(LogStatus.INFO, "Registration button clicked");
		
		//popuniti sljedeæa polja proizvoljnog sadržaja 
		//(First Name, Last Name, Marital Status, Hobby, Country, Date of Birth, Your Profile Picture,
		//About Yourself, Password, Confirme Password). Ne treba klikati na gumb Submit.
		
		driver.findElement(By.id("name_3_firstname")).sendKeys("Tomislav");
		driver.findElement(By.id("name_3_lastname")).sendKeys("Šapina");
		driver.findElement(By.cssSelector("input[type='radio'][value='married']")).click();
		driver.findElement(By.cssSelector("input[type='checkbox'][value='reading']")).click();
		
		country = new Select(driver.findElement(By.id("dropdown_7")));
		country.selectByValue("Croatia");
		
		month = new Select(driver.findElement(By.id("mm_date_8")));
		month.selectByValue("3");
		
		day = new Select(driver.findElement(By.id("dd_date_8")));
		day.selectByValue("17");
		
		year = new Select(driver.findElement(By.id("yy_date_8")));
		year.selectByValue("1991");
		
		driver.findElement(By.id("phone_9")).sendKeys("+385 97 777 5479");
		driver.findElement(By.id("username")).sendKeys("tomi20");
		driver.findElement(By.id("email_1")).sendKeys("tosapina@hotmail.com");
		driver.findElement(By.id("profile_pic_10")).sendKeys("C:\\Users\\Tomislav\\Desktop\\user.jpg");
		driver.findElement(By.xpath("//*[@id='description']")).sendKeys("Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec velit neque, auctor sit amet aliquam vel, ullamcorper sit amet ligula.");
		driver.findElement(By.id("password_2")).sendKeys("asdfghjkl12345");
		driver.findElement(By.id("confirm_password_password_2")).sendKeys("asdfghjkl12345");
		
		logger.log(LogStatus.INFO, "Form is filled in");
		
		//“Scrollati“ stranicu prema gore i potvrditi da se tekst Registration nalazi na stranici
		js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, 0)");
		
		logger.log(LogStatus.INFO, "Scrolled to top");
		
		 if(driver.findElement(By.tagName("body")).getText().contains("Registration")) 
		 {
			 System.out.println("Text Registration is on the page");
			 logger.log(LogStatus.INFO, "Registration text exist");
		 }
		 else
		 {
			 System.out.println("Text Registration isn't on the page");
			 logger.log(LogStatus.INFO, "Registration text  don't exist");
		 }

		 //Kliknuti na gumb Home
		 driver.findElement(By.cssSelector("a[title='Home']")).click();
		 logger.log(LogStatus.INFO, "Home link is clicked");
		 
		 //Na desnoj strani kliknuti na plavi gumb Droppable
		 driver.findElement(By.id("menu-item-141")).click();
		 
		 logger.log(LogStatus.INFO, "Dropable link is clicked");
		 
		 //Kvadrat “Drage me to my target“ povuæi u kvadrat “Drop here“
		 From = driver.findElement(By.id("draggableview"));
		 To = driver.findElement(By.id("droppableview"));
		 builder = new Actions(driver);
		 dragAndDrop = builder.clickAndHold(From).moveToElement(To).release(To).build();
		 dragAndDrop.perform();
		 
		 logger.log(LogStatus.INFO, "Element is dragged to another element");
		 
		//Kliknuti na gumb datepicker
		 driver.findElement(By.cssSelector("a[href='http://demoqa.com/datepicker/']")).click();
		 logger.log(LogStatus.INFO, "Datepicker is clicked");
		
		 //Postaviti proizvoljni datum
		 driver.findElement(By.id("datepicker1")).click();
		 driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[5]/a")).click();
		 
		 logger.log(LogStatus.INFO, "Date is choosen on datepicker");
		 
		 if(driver.findElement(By.tagName("body")).getText().contains("DatePicker")) 
		 {
			 System.out.println("Text DatePicker is on the page");
			 logger.log(LogStatus.INFO, "Text DatePicker  exist");
		 }
		 else
		 {
			 System.out.println("Text DatePicker  isn't on the page");
			 logger.log(LogStatus.INFO, "Text DatePicker  don't exist");
		 }
		 
		
	}
	
	
}
