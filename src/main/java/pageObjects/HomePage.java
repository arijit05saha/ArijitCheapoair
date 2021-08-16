package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
	
	public WebDriver driver;
	
	private By cheapoairLogo = By.cssSelector(".header__logo");
	private By dep = By.cssSelector("[aria-label*='departure']");
	private By ret = By.cssSelector("[aria-label*='return']");
	private By months_by = By.cssSelector(".calendar__month");
	private By month_by = By.xpath("//*[@id='calendar']/div //*[@class='calendar__single-month active'][1]/div");
	private By monthPlus_by = By.cssSelector(".calendar-nav__next");
	private By dates_by = By.xpath("//*[@id=\"calendar\"]/div //*[@class='calendar__single-month active'][1]/div[3]/div/a");
	private By departureCityBox_by = By.xpath("//*[@aria-describedby='from0autoSuggestLabel']");
	private By arrivalCityBox_by = By.xpath("//*[@aria-describedby='to0autoSuggestLabel']");
	private By departureCity_by = By.xpath("//ul[@class='suggestion-box__list'][1]/li");
	private By arrivalCity_by = By.xpath("//div[@class='row']/div[2]/div/section/div/ul/li");
	private By travellerBtn = By.id("travellerButton");
	private By adultTravellers_by = By.id("lbladults");
	private By addAdults_by = By.id("addadults");
	private By removeAdults_by = By.id("removeadults");
	private By searchNow = By.id("searchNow");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getLogo() {
		return driver.findElement(cheapoairLogo);
	}
	
	public void selectDepCity(String city){	
		WebElement depCity = driver.findElement(departureCityBox_by);
	
		Actions act = new Actions(driver);
		act.keyDown(depCity, Keys.SHIFT).sendKeys(city).keyDown(depCity, Keys.SHIFT).build().perform();
		depCity.sendKeys(Keys.ENTER);
	}
	
	public WebElement getDepCity() {
		return driver.findElement(departureCityBox_by);
	}
	
	public void selectArrCity(String city) throws InterruptedException{
		WebElement arrCity = driver.findElement(By.xpath("//*[@aria-describedby='to0autoSuggestLabel']"));
		arrCity.sendKeys(city);
		List<WebElement> cityList = driver.findElements(By.xpath("//*[@id='to0autoSuggestLabel']/following-sibling::div/ul/li/span/span"));
		for (int i = 1; i < cityList.size(); i++ ) {
			System.out.println(cityList.get(i).getText());
			if (cityList.get(i).getText().toLowerCase().contains(city.toLowerCase())) {
				cityList.get(i).click();
			}
		}
////		//*[@id='to0autoSuggestLabel']/following-sibling::div/ul/li[2]/span/span
////		arrCity.sendKeys(Keys.SHIFT,city);	
//		arrCity.sendKeys(Keys.ENTER);
	}
	
	public WebElement getArrCity() {
		return driver.findElement(arrivalCityBox_by);
	}
	
	public WebElement selectDepart() {
		return driver.findElement(dep);
	}
	
	public WebElement selectReturn() {
		return driver.findElement(ret);
	}
	
	public WebElement navigateMonth(String month) {
		List<WebElement> months = driver.findElements(months_by);	
		
		for (int i=0; i < months.size(); i++) {
			if (driver.findElement(month_by).getText().contains(month)) {
				return driver.findElement(month_by);
			}
			else {
				driver.findElement(monthPlus_by).click();
			}
		}
		return null;
	}
	
	public WebElement navigateDate(String date) {		
		List<WebElement> dates = driver.findElements(dates_by);
		for (int d = 1; d< dates.size(); d++) {		
			if (dates.get(d).getText().equals(date)) {
				return dates.get(d);
			}
		}
		return null;
	}
	
	public WebElement selectNoOfPassengers(int nOfAdults){
		
		driver.findElement(travellerBtn).click();
		WebElement nOfAdults_webElem = driver.findElement(adultTravellers_by);
		
		for (int i = 1; i < nOfAdults; i++) {
			if (nOfAdults_webElem.getText().equals(String.valueOf(nOfAdults))) {
				break;
			}
			else if (Integer.parseInt(nOfAdults_webElem.getText()) < nOfAdults){
				driver.findElement(addAdults_by).click();
			}
			else if (Integer.parseInt(nOfAdults_webElem.getText()) > nOfAdults){
				driver.findElement(removeAdults_by).click();
			}
		}
		return driver.findElement(By.id("closeDialog"));
	}
	
	public WebElement getNoOfPassengers() {
		driver.findElement(travellerBtn).click();
		return driver.findElement(adultTravellers_by);
	}
	
	public WebElement search() {
		return driver.findElement(searchNow);
	}	
}
