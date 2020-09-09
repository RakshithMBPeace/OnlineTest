package drivers;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {

    public static String workingDir = System.getProperty("user.dir");
    private static String downloadPath = workingDir + "\\Downloads";
    private static String chromeDownloadPath = workingDir +"/src/Downloads/chromedriver.exe";

    public static WebDriver getManager(DriverType type) {

        WebDriver driver;

        switch (type) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", chromeDownloadPath);
                ChromeOptions chOptions = new ChromeOptions();
                chOptions.addArguments("--disable-notifications");
                driver =  new ChromeDriver();
                break;
            case FIREFOX:
                System.setProperty("WebDriver.firefox.driver", downloadPath);
                driver =  new FirefoxDriver();
                break;
            case IE:
                System.setProperty("WebDriver.gecko.driver", downloadPath);
                driver =  new InternetExplorerDriver();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return driver;
    }
}
