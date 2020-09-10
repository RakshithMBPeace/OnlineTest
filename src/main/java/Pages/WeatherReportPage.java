package Pages;

import Core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.log4testng.Logger;


public class WeatherReportPage extends BasePage {
    public static final Logger logger = Logger.getLogger(WeatherReportPage.class);
    public static String txtTempInCelcius;
    public static String txtTempInfarenheit;
    public WebDriver driver;
    public WeatherReportPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

    public static final By lblPinYourCity = By.xpath("//span[text()='Pin your City']");
    public static final By inpSearchBox = By.id("searchBox");

    public WebElement getLblPinYourCity() { return waitForElement(lblPinYourCity);}
    public WebElement getInpSearchBox() { return waitForElement(inpSearchBox);}

    public void iVerifyEneteredCityOnWeatherPage(String city) throws InterruptedException {
        waitForPageLoad();
        waitFor(10000);
        waitForElement(getLblPinYourCity());
        enterText(getInpSearchBox(), city);
        Actions act = new Actions(this.driver);
        act.sendKeys(Keys.ENTER).build().perform();
        waitFor(20000);
        if(driver.findElement(By.xpath("//input[@type='checkbox'][@id='"+city+"']")).isSelected()) {
            Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + city + "']")).isDisplayed());
        }else {
            click(driver.findElement(By.xpath("//input[@type='checkbox'][@id='" + city + "']")));
            Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + city + "']")).isDisplayed());
        }
        txtTempInCelcius = driver.findElement(By.xpath("//div[text()="+city+"]/preceding-sibling::div/span[@class='tempRedText']")).getText();
        txtTempInfarenheit = driver.findElement(By.xpath("//div[text()="+city+"]/preceding-sibling::div/span[@class='tempWhiteText']")).getText();
    }
}
