package SeleniumJava.AutomationPractice;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import resources.Base;

public class ValidateLogo extends Base{
	
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	@BeforeTest
	public void initialize() throws IOException {
		
		driver = InitializeDriver();
		driver.get(prop.getProperty("url"));
		log.info("Driver is initialized");
	}
	
	@Test
	public void validateCheapoAirLogo() {
		HomePage hp = new HomePage(driver);
		Assert.assertEquals(hp.getLogo().getText(), "CheapOair");
	}

}
