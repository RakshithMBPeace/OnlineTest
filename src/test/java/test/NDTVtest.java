package test;

import Pages.HomePage;
import Pages.WeatherReportPage;
import listeners.extent.ExtentReportListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class NDTVtest extends ExtentReportListener {

    @Test
    public void TestToValidateWeatherSectionFromNDTVWebsite() throws Exception{
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        WeatherReportPage weatherPage = new WeatherReportPage(driver);
        homePage.iLaunchNDTVHomePage();
        homePage.iClickOnWeatherTab();
        weatherPage.iVerifyEneteredCityOnWeatherPage("Bengaluru");
    }
}
