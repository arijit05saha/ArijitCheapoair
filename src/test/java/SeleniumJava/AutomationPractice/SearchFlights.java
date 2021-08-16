package SeleniumJava.AutomationPractice;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import resources.Base;
import resources.Utilities;

public class SearchFlights extends Base{
	
	private static Logger log = LogManager.getLogger(SearchFlights.class.getName());
	
	public WebDriver driver;
	
	
	@BeforeTest
	public void initialize() throws IOException {
		driver = InitializeDriver();
		driver.get(prop.getProperty("url"));
		log.info("Driver is initialized and URL is opened!");
	}
	
//	@Test
//	public void readData() throws IOException, InterruptedException{
//		String testInputFileName = prop.getProperty("testdata");
//		String testInputFile = System.getProperty("user.dir") + "\\" + testInputFileName;
//		
//		System.out.println(testInputFile);
//		
//		Utilities ut = new Utilities();
//		ut.readExcel(testInputFile);
//	}
	
	@Test
	public void searchFlights() throws IOException, InterruptedException{
		HomePage hp = new HomePage(driver);
		
//		String testInputFile = prop.getProperty("testData");
//		
//		Utilities ut = new Utilities();
//		ut.readExcel(testInputFile);
		
		hp.selectDepCity("NYC");
//		WebDriverWait wait_depCity = new WebDriverWait(driver,10);
//		wait_depCity.until(ExpectedConditions.textToBePresentInElementValue(hp.getArrCity(), "NYC - New York All Airports, New York, United States"));
		log.info("Departure city set correctly to " + hp.getDepCity().getAttribute("value"));
		
		hp.selectArrCity("TPA");
		WebDriverWait wait_arrCity = new WebDriverWait(driver,20);
		wait_arrCity.until(ExpectedConditions.textToBePresentInElementValue(hp.getArrCity(), "TPA"));
		log.info("Arrival city set correctly to " + hp.getArrCity().getAttribute("value"));
		
		String month_dep = "December";
		String day_dep = "20";
		String expected_dep = month_dep.substring(0, 3) + " " + day_dep;
		hp.selectDepart().click();
		hp.navigateMonth(month_dep);
		hp.navigateDate(day_dep).click();
		String depDate = hp.selectDepart().getAttribute("value");
		log.info("Departure date selected successfully " + depDate);
		
		String month_arr = "December";
		String day_arr = "20";
		String expected_arr = month_arr.substring(0, 3) + " " + day_arr;
		hp.navigateMonth(month_arr);
		hp.navigateDate(day_arr).click();
		String returnDate = hp.selectReturn().getAttribute("value");
		log.info("Return date selected successfully " + returnDate);
				
		hp.selectNoOfPassengers(5).click();
		String numOfPassengers = hp.getNoOfPassengers().getText();
		log.info("Number of passengers set successfully " + numOfPassengers);
		
		Assert.assertEquals(hp.getDepCity().getAttribute("value"), "NYC - New York All Airports, New York, United States");
		Assert.assertEquals(hp.getArrCity().getAttribute("value"), "TPA - Tampa, Florida, United States");
		Assert.assertEquals(depDate, expected_dep);
		Assert.assertEquals(returnDate, expected_arr);
		Assert.assertEquals(numOfPassengers, "5");
		
		hp.search().click();
		log.info("Search button clicked!");
	}
	
	
	@AfterTest
	public void teardown() {
		driver.close();
	}
}
