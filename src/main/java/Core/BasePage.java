package Core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BasePage {
        private static final Logger logger = LoggerFactory.getLogger(BasePage.class);public WebDriverWait externalWait;
        public WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
            this.webDriver= webDriver;
            PageFactory.initElements(webDriver, this);
        }

    public WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = webDriver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

        public BasePage waitForPageLoad()  {
            logger.info("Waiting for element to be present in element");
            JavascriptExecutor jsScript =(JavascriptExecutor) this.webDriver;
            jsScript.executeScript("return document.readyState", new Object[0]).toString().equals("complete");
            return this;
        }

        public BasePage waitForElementToBeClickable(WebElement element) {
            WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
            logger.info("Waiting for element to clickable ");
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return this;
        }

    public void waitFor(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

        public Boolean waitUntilElementVisible(By locator)  {
            Boolean isVisible = false;
            WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
            WebElement element = (WebElement)wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (element.isDisplayed()) {
                isVisible = true;
                logger.info("Element is visible " + locator.toString());
            } else {
                logger.error("Unable to find element");
            }

            return isVisible;
        }

        public WebElement waitForElement(int waitDurationInSeconds, By locator) {
            logger.info("Waiting for element for duration" + waitDurationInSeconds);
            WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
            return (WebElement)wait.until(ExpectedConditions.elementToBeClickable(locator));
        }

    public BasePage waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

        public BasePage waitForAlert(int waitDurationInSeconds, By locator) {
            logger.info("Waiting for alert to be present " + waitDurationInSeconds);
            WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
            wait.until(ExpectedConditions.alertIsPresent());
            return this;
        }

        public BasePage waitForElementToBeSelected(int waitDurationInSeconds, WebElement element) {
            logger.info("Waiting for alert to be present " + waitDurationInSeconds);
            WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
            wait.until(ExpectedConditions.elementToBeSelected(element));
            return this;
        }

        public BasePage click(WebElement element) {
            logger.info("Clicking the element in page");
            this.waitForElementToBeClickable(element);
            element.click();
            return this;
        }

        public BasePage javaScriptClick(WebElement element) {
            logger.info("Javascript click ");
            JavascriptExecutor executor = (JavascriptExecutor) this.webDriver;
            executor.executeScript("arguments[0].click();", new Object[]{element});
            return this;
        }

        public BasePage scrollToMiddleOfViewPort(WebElement element) {
            logger.info("Scroll to middle of view port ");
            JavascriptExecutor executor = (JavascriptExecutor) this.webDriver;
            executor.executeScript("browser.executeScript(function scrollCenter(elem) {var win = elem.ownerDocument.defaultView || window, box = elem.getBoundingClientRect(), dy = box.top - (win.innerHeight - box.height) / 2; win.scrollTo(win.pageXOffset, win.pageYOffset + dy);}, element);", new Object[]{element});
            return this;
        }

        public BasePage moveToElement(WebElement element) {
            logger.info("Moving mouse to an element");
            Actions actions = new Actions(this.webDriver);
            this.waitForElementToBeClickable(element);
            actions.moveToElement(element).perform();
            return this;
        }

        public BasePage enterText(WebElement element, String textToEnter) {
            if (textToEnter != null && !textToEnter.isEmpty()) {
                logger.info("Entering the text -" + textToEnter);
                this.waitForElementToBeClickable(element);
                element.sendKeys(new CharSequence[]{textToEnter});
            }
            return this;
        }

        public BasePage enterTextSlowly(WebElement element, String textToEnter) throws InterruptedException {
            if (textToEnter != null && !textToEnter.isEmpty()) {
                logger.info("Typing characters slowly --" + textToEnter);
                this.waitForElementToBeClickable(element);
                char[] chars = textToEnter.toCharArray();
                char[] var4 = chars;
                int var5 = chars.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    char character = var4[var6];
                    Thread.sleep(10L);
                    element.sendKeys(new CharSequence[]{Character.toString(character)});
                }
            }

            return this;
        }

        public BasePage clearTextFields(WebElement element) {
            this.waitForElementToBeClickable(element);
            element.clear();
            logger.info("Clearing Text fileds:" + element);
            return this;
        }

        public BasePage enterTextByClearingFields(WebElement element, String textToEnter) {
            if (textToEnter != null && !textToEnter.isEmpty()) {
                this.waitForElementToBeClickable(element);
                element.sendKeys(new CharSequence[]{Keys.HOME, Keys.chord(new CharSequence[]{Keys.SHIFT, Keys.END}), textToEnter});
            }

            return this;
        }

        public BasePage enterTextSlowlyByClearingFields(WebElement element, String textToEnter) throws InterruptedException {
            if (textToEnter != null && !textToEnter.isEmpty()) {
                logger.info("Typing characters slowly after clearing the text--" + textToEnter);
                this.waitForElementToBeClickable(element);
                element.sendKeys(new CharSequence[]{Keys.HOME, Keys.chord(new CharSequence[]{Keys.SHIFT, Keys.END})});
                char[] chars = textToEnter.toCharArray();
                char[] var4 = chars;
                int var5 = chars.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    char character = var4[var6];
                    Thread.sleep(10L);
                    element.sendKeys(new CharSequence[]{Character.toString(character)});
                }
            }
            return this;
        }

        public String getText(WebElement element) {
            return element.getText();
        }

        public BasePage scrollDownBottompage() {
            JavascriptExecutor jse = (JavascriptExecutor) this.webDriver;
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight)", new Object[0]);
            logger.info("Scrolled down to the bottom of the page");
            return this;
        }

        public BasePage scrollUpTopPage() {
            JavascriptExecutor jse = (JavascriptExecutor) this.webDriver;
            jse.executeScript("window.scrollTo(0, -document.body.scrollHeight)", new Object[0]);
            logger.info("Scrolled up to the top of the page");
            return this;
        }

        public boolean clickWebAlert(String btnText) {
            boolean isAlertClicked = false;

            try {
                Alert alert = this.webDriver.switchTo().alert();
                if (!btnText.equalsIgnoreCase("OK") && !btnText.equalsIgnoreCase("continue") && !btnText.equalsIgnoreCase("accept")) {
                    if (btnText.equalsIgnoreCase("CANCEL") || btnText.equalsIgnoreCase("dismiss")) {
                        alert.dismiss();
                        isAlertClicked = true;
                    }
                } else {
                    alert.accept();
                    isAlertClicked = true;
                }
            } catch (Exception var4) {
                logger.info("WebAlert or WebAlert Button not found");
                isAlertClicked = false;
            }

            return isAlertClicked;
        }

        public String getAlertMsgText() {
            String alertText = null;

            try {
                Alert alert = this.webDriver.switchTo().alert();
                alertText = alert.getText();
            } catch (Exception var3) {
                logger.info("WebAlert not displayed");
                alertText = null;
            }

            return alertText;
        }


        public BasePage selectValueFromDropdown(WebElement selectElement, String value) {
            boolean isSelected = false;
            if (selectElement == null && value == null) {
                logger.info("Element locator or  value is not set");
            } else {
                Select select = new Select(selectElement);
                if (!isSelected) {
                    try {
                        select.selectByIndex(Integer.parseInt(value));
                        isSelected = true;
                    } catch (Exception var8) {
                        isSelected = false;
                    }
                }

                if (!isSelected) {
                    try {
                        select.selectByVisibleText(value);
                        isSelected = true;
                    } catch (Exception var7) {
                        isSelected = false;
                    }
                }

                if (!isSelected) {
                    try {
                        select.selectByValue(value);
                        isSelected = true;
                    } catch (Exception var6) {
                        isSelected = false;
                    }
                }
            }

            return this;
        }

        public boolean isElementDisplayed(WebElement element) {
            logger.info("Checking whether element is displayed");

            try {
                return element.isDisplayed();
            } catch (NoSuchElementException var3) {
                logger.info("No element found");
                return false;
            }
        }



        public BasePage waitForPresenceOfElement(int waitDurationInSeconds, By element) {
            logger.info("Waiting for element to be present " + waitDurationInSeconds);
            WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(element));
            return this;
        }

        public BasePage waitForElementStaleness(int waitDurationInSeconds, By locator) {
            logger.info("Waiting for staleness  state of element" + waitDurationInSeconds);
            WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
            wait.until(ExpectedConditions.stalenessOf(this.webDriver.findElement(locator)));
            return this;
        }


        public boolean isAlertPresent() {
            return isAlertPresent(this.webDriver);
        }

        public boolean isAlertPresent(WebDriver driver) {
            try {
                WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = driver.switchTo().alert();
                String str = alert.getText();
                alert.accept();
                logger.info("Alert displayed with message -" + str);
                return true;
            } catch (Exception var4) {
                logger.debug("No Alert displayed");
                return false;
            }
        }
   }

















