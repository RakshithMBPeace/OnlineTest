package Pages;

import Core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static Utils.LoadProperties.getPropertyValue;

public class HomePage extends BasePage {

    public WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver= driver;
    }

    public static final By labelNDTVTab = By.xpath("//a[text()='NDTV']");
    public static final By linkWeather = By.xpath("//a[text()='WEATHER']");
    public static final By linkExtend = By.id("h_sub_menu");

    public WebElement getLabelNDTVTab() { return waitForElement(labelNDTVTab);}
    public WebElement getLinkWeather() { return waitForElement(linkWeather);}
    public WebElement getLinkExtend() { return waitForElement(linkExtend);}

    public void iLaunchNDTVHomePage() throws Exception {
        String appUrl = getPropertyValue("appUrl");
        driver.navigate().to(appUrl);
        waitForElement(getLabelNDTVTab());
    }

    public void iClickOnWeatherTab()  {
      if(isElementDisplayed(getLinkExtend())){
          waitForPageLoad();
          waitFor(10000);
          click(getLinkExtend());
          click(getLinkWeather());
      }
      else{
          waitForPageLoad();
          waitFor(10000);
          click(getLinkWeather());
      }

    }



}
