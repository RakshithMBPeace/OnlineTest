package tests.UITests;

import Pages.HomePage;
import Pages.WeatherReportPage;
import listeners.extent.ExtentReportListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import static Utils.LoadProperties.*;

public class NDTVtest extends ExtentReportListener {

    @Test
    public void TestToValidateWeatherSectionFromNDTVWebsite() throws Exception{
        String cityName= getPropertyValue("City");
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        WeatherReportPage weatherPage = new WeatherReportPage(driver);
        homePage.iLaunchNDTVHomePage();
        homePage.iClickOnWeatherTab();
        weatherPage.iVerifyEneteredCityOnWeatherPage(cityName);
    }
}
