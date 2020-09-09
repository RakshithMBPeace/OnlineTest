package drivers;

import org.openqa.selenium.WebDriver;


public abstract class DriverProvider {
    protected WebDriver driver;

    public abstract void startBrowserService();

    public abstract void stopBrowserService();

    public abstract WebDriver createDriver();

    /** Quit Webdriver instance */
    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Get webdriver instance
     *
     * @return webdriver
     */
    public WebDriver getDriver() {
        return createDriver();
    }
}
