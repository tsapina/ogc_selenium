package oGC;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class parentOGC {
	
	public WebDriver driver;
	public ATUTestRecorder recorder;
	
	public ExtentReports report;
	public ExtentTest logger;
	// initialize the HtmlReporter
	
	@BeforeTest
	public void startReport() 
	{
		//true parametar =  replace existing report
		report = new ExtentReports(System.getProperty("user.dir") + "/test-output/Report.html", true);
		report.addSystemInfo("HostName", "Tomislav Host").addSystemInfo("Enviroment", "QA");
		report.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
	}
	
	
	@BeforeMethod
	public void beforeTest() throws ATUTestRecorderException 
	{
		logger = report.startTest("Test is starting");
		
		recorder = new ATUTestRecorder("C:\\Users\\Tomislav\\Desktop", "test", false);
		recorder.start();
		
		//postaviti Chrome (chromedriver) kao testni preglednik
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tomislav\\Desktop\\chromedriver.exe");
		driver = new ChromeDriver();
		logger.log(LogStatus.INFO, "Browser is started");
		
		//Postaviti preglednik preko cijelog ekrana (maximize window)
		driver.manage().window().maximize();
		logger.log(LogStatus.INFO, "Browser is maximized");

		//Implicitno vrijeme èekanja postaviti na 15 sekundi
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		logger.log(LogStatus.INFO, "Implicity waiting set");
		
		
	}
	
	@AfterMethod
	public void afterTest(ITestResult result) throws ATUTestRecorderException 
	{
		//zatvoriti preglednik
		driver.close();
		recorder.stop();
		if(result.getStatus() == ITestResult.FAILURE)
		{
			logger.log(LogStatus.FAIL, result.getThrowable());
		}
		logger.log(LogStatus.INFO, "Test is ended");
	
		report.endTest(logger);
	}
	
	@AfterTest
	public void endReport() 
	{
		report.flush();
		report.close();
	}
	
}
