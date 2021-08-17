package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver InitializeDriver() throws IOException {
		String projectPath = System.getProperty("user.dir");
		String resourcesPath = projectPath + "//src/main/java/resources";
		String webdriversPath = projectPath + "//src/main/java/webdrivers";
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream(resourcesPath + "\\data.properties");
		prop.load(fis);
		
		//String browser = prop.getProperty("browser");
		String browser = System.getProperty("browser");
		String url = prop.getProperty("url");
		
		if (browser.toLowerCase().contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", webdriversPath + "\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			
			if (browser.toLowerCase().contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
		}
		
		else if (browser.toLowerCase().contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", webdriversPath + "\\geckodriver.exe");
			driver = new ChromeDriver();
		}
		
		else if (browser.toLowerCase().contains("ie")) {
			System.setProperty("webdriver.ie.driver", webdriversPath + "\\IEDriver.exe");
			driver = new ChromeDriver();
		}
		
		else {
			System.out.println("Browser option not specified correctly");
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		return driver;
		
	}
	


}
