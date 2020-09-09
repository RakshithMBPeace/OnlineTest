package Core;

import static Utils.LoadProperties.getBrowserType;
import drivers.BrowserFactory;
import drivers.DriverType;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class TestBase {
    private static final Logger logger = LoggerFactory.getLogger(TestBase.class);
    public WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws Exception {
        // Todo get required browser type from parameter
        logger.info("before suite");
        String browserType = getBrowserType().toUpperCase();
        driver = BrowserFactory.getManager(DriverType.valueOf(browserType));
      //webDriverThreadLocal.set(BrowserFactory.getManager(DriverType.valueOf(browserType)));
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        logger.info("After suite");
        driver.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        if (isAlertPresent()) {
            driver.close();
        } else {
            driver.close();
        }
    }


    public WebDriver getDriver() {
        return this.driver;
    }
    public boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, 10);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String str = alert.getText();
            alert.accept();
            logger.info("Alert displayed with message -" + str);
            return true;
        } catch (Exception Ex) {
            logger.debug("No Alert displayed");
            return false;
        }
    }

}
